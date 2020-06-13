package com.srgpanov.itmonitoring.di

import android.content.Context
import androidx.room.Room
import com.srgpanov.itmonitoring.data.local.CurrencyDao
import com.srgpanov.itmonitoring.data.local.CurrencyDataBase
import com.srgpanov.itmonitoring.data.local.LocalDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule() {
    @Singleton
    @Provides
    fun providesDataBase(context: Context):CurrencyDataBase{
        return CurrencyDataBase.getInstance(context)
    }

    @Singleton
    @Provides
    fun providesCurrencyDao(db:CurrencyDataBase):CurrencyDao{
        return db.currencyDao()
    }



}