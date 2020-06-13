package com.srgpanov.itmonitoring.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.srgpanov.itmonitoring.data.model.DayCurs
import com.srgpanov.itmonitoring.data.model.ValCurs
import com.srgpanov.itmonitoring.data.model.Valute


@Database(
    entities = arrayOf(
        DayCurs::class,
        Valute::class
    ), version = 1,exportSchema = false
)
abstract class CurrencyDataBase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao

    companion object {
        private lateinit var INSTANCE: CurrencyDataBase

        fun getInstance(context: Context): CurrencyDataBase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(CurrencyDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        CurrencyDataBase::class.java, "currencyDb"
                    ).build()
                }
            }
            return INSTANCE
        }
    }
}