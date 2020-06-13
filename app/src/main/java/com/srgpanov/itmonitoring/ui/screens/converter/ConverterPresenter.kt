package com.srgpanov.itmonitoring.ui.screens.converter

import android.util.Log
import com.srgpanov.itmonitoring.data.Repository
import com.srgpanov.itmonitoring.data.model.Valute
import com.srgpanov.itmonitoring.di.ConverterScope
import com.srgpanov.itmonitoring.other.addInCompositeDisp
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.lang.NullPointerException
import java.util.concurrent.TimeUnit
import java.util.function.Consumer
import javax.inject.Inject


open class ConverterPresenter constructor(private val repository: Repository):ConverterContract.Presenter {
    private var view:ConverterContract.View?=null
    private var inputValue=1f
    private var outputValue=0f
    private val rub = Valute("1","RUB","1",1,643,"Российский рубль")
    private val usd = Valute("R01215","USD","68,6183",1,840,"Доллар США")
    var inputCurrency:Valute=rub
    var outputCurrency:Valute=usd
    private var dataFromLocal = false
    private val compDisposable:CompositeDisposable= CompositeDisposable()
    private val availableValute = mutableListOf<Valute>()
    private val valCurs: Single<List<Valute>> = repository
        .getCurrentCurrency()
        .doOnError {
            onLoadFailureObserver()
        }
        .map { list->
            sortAndAddRUB(list)
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    private val valCursObserver: SingleObserver<List<Valute>> = object : SingleObserver<List<Valute>> {
        override fun onSuccess(list: List<Valute>) {
                availableValute.clear()
                availableValute.addAll(list)
                dataFromLocal=false
                setupDefaultCurrency(list)
                val state = ConvertState(inputValue,outputValue,inputCurrency,outputCurrency,dataFromLocal)
                view?.restoreState(state)
            }
        override fun onSubscribe(d: Disposable) {
            compDisposable.add(d)
        }
        override fun onError(e: Throwable) {
            Log.e(TAG, "error on loading currency $e" )
        }

    }
    init {
            valCurs.subscribe(valCursObserver)
    }

    companion object{
        const val TAG="ConverterPresenter"
    }

    override fun convertCurrency() {
        try {
            val outputCurs=(inputCurrency.value()!!/(inputCurrency.nominal))/(outputCurrency.value()!!/outputCurrency.nominal)
            outputValue = outputCurs*inputValue
            view?.showConvertedCurrency(outputValue)
        }catch (e:NullPointerException){
            Log.e(TAG, "convertCurrency: $e")
        }
    }

    override fun refresh() {
        valCurs.subscribe(valCursObserver)
        Log.d(TAG, "refresh: ${compDisposable.size()}")
    }


    override fun onInputValueChanged(value: Float) {
        Log.d(TAG, "onInputValueChanged: $value")
        inputValue=value
    }

    override fun getAvailableValuate(): List<Valute> {
        return availableValute
    }

    override fun onInputValuteChanged(valute: Valute) {
        inputCurrency=valute
        convertCurrency()
    }

    override fun onOutputValuteChanged(valute: Valute) {
        outputCurrency=valute
        convertCurrency()
    }

    override fun bindView(view: ConverterContract.View) {
        this.view=view
        val state = ConvertState(inputValue,outputValue,inputCurrency,outputCurrency,dataFromLocal)
        view.restoreState(state)
    }

    override fun unbindView() {
        view=null
    }

    override fun onRelease() {
        compDisposable.clear()
    }

    private fun onLoadFailureObserver(): Disposable {
        val disp = repository.getLastCurrency()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list ->
                Log.d(TAG, "onLoadFailureObserver")
                availableValute.clear()
                availableValute.addAll(list)
                dataFromLocal = true
                setupDefaultCurrency(list)
                convertCurrency()
            }, { it: Throwable? ->
                Log.e(TAG, " ", it)
            })
        compDisposable.add(disp)
        return disp

    }
    private fun setupDefaultCurrency(list: List<Valute>) {
        val find = list.find {
            it.charCode == outputCurrency.charCode
        }
        if (find != null) {
            outputCurrency=find
            inputCurrency.date=find.date
            outputValue = inputValue * (find.value() ?: 1f)
        }
        Log.d(TAG, "setupDefaultCurrency: $find")
    }
    private fun sortAndAddRUB(list: List<Valute>): List<Valute> {
        val mutList = list.toMutableList()
        Log.d(TAG, "get ${mutList.first()} ")
        mutList.add(rub)
        return mutList.sortedBy { it.charCode }
    }
}