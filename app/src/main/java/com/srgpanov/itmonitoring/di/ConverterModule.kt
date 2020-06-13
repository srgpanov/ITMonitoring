package com.srgpanov.itmonitoring.di

import com.srgpanov.itmonitoring.data.Repository
import com.srgpanov.itmonitoring.ui.screens.converter.ConverterPresenter
import dagger.Module
import dagger.Provides

@Module
class ConverterModule {

    @ConverterScope
    @Provides
    fun provideConverterPresenter(repository: Repository):ConverterPresenter{
        return ConverterPresenter(repository)
    }
}