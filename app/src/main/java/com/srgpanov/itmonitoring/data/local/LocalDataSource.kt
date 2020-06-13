package com.srgpanov.itmonitoring.data.local

import android.util.Log
import com.srgpanov.itmonitoring.data.model.ValCurs
import com.srgpanov.itmonitoring.data.remote.CurrencyAPI
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.internal.operators.completable.CompletableFromAction
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val dao: CurrencyDao) {

    companion object{
        const val TAG="LocalDataSource"
    }
    fun saveValCurs(valCurs: ValCurs):Completable{


        return CompletableFromAction {
            dao.insertValCurs(valCurs)
        }
    }
    fun getLastValCurs()=dao.getValCurs()

}