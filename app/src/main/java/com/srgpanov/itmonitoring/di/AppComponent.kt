package com.srgpanov.itmonitoring.di

import com.srgpanov.itmonitoring.data.Repository
import com.srgpanov.itmonitoring.ui.screens.converter.ConverterFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class,NetworkModule::class,RoomModule::class])
interface AppComponent {
    fun createConverterComponent():ConverterComponent

}