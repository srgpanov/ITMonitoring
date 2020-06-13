package com.srgpanov.itmonitoring.di

import android.content.Context
import com.srgpanov.itmonitoring.App
import com.srgpanov.itmonitoring.data.Repository
import com.srgpanov.itmonitoring.data.local.LocalDataSource
import com.srgpanov.itmonitoring.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {
    @Singleton
    @Provides
    fun provideApp():App{
        return app
    }

    @Singleton
    @Provides
    fun provideAppContext(app: App): Context {
        return app.applicationContext
    }

}