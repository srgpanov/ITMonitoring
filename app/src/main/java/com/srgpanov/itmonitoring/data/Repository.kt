package com.srgpanov.itmonitoring.data

import android.util.Log
import com.srgpanov.itmonitoring.data.local.LocalDataSource
import com.srgpanov.itmonitoring.data.model.ValCurs
import com.srgpanov.itmonitoring.data.model.Valute
import com.srgpanov.itmonitoring.data.remote.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.SingleSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource) {
    
    companion object{
        const val TAG="Repository"
    }
    fun getCurrentCurrency(): Single<List<Valute>> {
        return remoteDataSource.getCurrency()
            .map {valCurs->
                valCurs.valute.forEach {valute->
                    valute.date=valCurs.date
                }
                Log.d(TAG, "getCurrentCurrency: ${valCurs.valute.first().date}")
                valCurs
            }
            .doAfterSuccess {
                localDataSource.saveValCurs(it).subscribe(
                    {
                        Log.d(TAG, "getCurrentCurrency: valCursSaved")
                    },{
                        Log.e(TAG, "getCurrentCurrency: $it ")
                    }
                )
            }
            .flatMap {
                Observable.fromIterable(it.valute)
            Single.just(it.valute)
        }
    }
    fun getLastCurrency(): Single<List<Valute>> =localDataSource.getLastValCurs().flatMap {list->
         Single.just(list.first().valute)
    }
}