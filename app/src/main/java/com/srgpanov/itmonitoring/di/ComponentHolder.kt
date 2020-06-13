package com.srgpanov.itmonitoring.di

import android.util.Log
import com.srgpanov.itmonitoring.App

class ComponentHolder(private val app: App) {
    lateinit var appComponent: AppComponent
    private var converterComponent:ConverterComponent?=null


    fun init(){
        appComponent=DaggerAppComponent.builder()
            .appModule(AppModule(app)).build();
    }

    fun getConverterComponent(): ConverterComponent {
        Log.d("ComponentHolder", "$converterComponent" )
        if (converterComponent == null) {
            converterComponent = appComponent.createConverterComponent()
        }
        return converterComponent!!
    }
    //метод для закрытия работы компонента, не используется так как в приложении всего 1 полноценный экран
    fun releaseConverterComponent() {
        Log.d("ComponentHolder", "$converterComponent" )
        converterComponent = null
        Log.d("ComponentHolder", "$converterComponent" )
    }
}