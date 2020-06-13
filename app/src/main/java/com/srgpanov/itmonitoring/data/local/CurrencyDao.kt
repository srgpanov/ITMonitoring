package com.srgpanov.itmonitoring.data.local

import android.util.Log
import androidx.room.*
import com.srgpanov.itmonitoring.data.model.DayCurs
import com.srgpanov.itmonitoring.data.model.ValCurs
import com.srgpanov.itmonitoring.data.model.Valute
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
abstract class CurrencyDao (){
    companion object{
        const val TAG= "CurrencyDao"
    }
    @Transaction
    open fun insertValCurs(valCurs: ValCurs){
        Log.d(TAG, "insertValCurs: ${valCurs.toDayCurs().date} ${valCurs.valute[0].date}")
        insertDayCurs(valCurs.toDayCurs())
        insertValute(valCurs.valute)
    }
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insertDayCurs(dayCurs: DayCurs)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insertValute(valute: Valute)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    internal abstract fun insertValute(valute: List<Valute>)

    @Transaction
    @Query("SELECT name,date FROM ValCurs ORDER BY timeStamp DESC LIMIT 1")
    abstract fun getValCurs(): Single<List<ValCurs>>
}
