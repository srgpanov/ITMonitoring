package com.srgpanov.itmonitoring

import android.app.Application
import com.facebook.stetho.Stetho
import com.srgpanov.itmonitoring.di.ComponentHolder

class App:Application() {
    lateinit var componentsHolder: ComponentHolder
    companion object{
        lateinit var instance:App
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance=this
        componentsHolder= ComponentHolder(this)
        componentsHolder.init()
        Stetho.initializeWithDefaults(this);
    }
}