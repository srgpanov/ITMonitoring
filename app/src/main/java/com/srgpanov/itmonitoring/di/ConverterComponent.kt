package com.srgpanov.itmonitoring.di

import com.srgpanov.itmonitoring.ui.screens.converter.ConverterFragment
import com.srgpanov.itmonitoring.ui.screens.converter.ConverterPresenter
import dagger.Subcomponent
import javax.inject.Singleton

@ConverterScope
@Subcomponent(modules = [ConverterModule::class])
interface ConverterComponent {
    fun injectConverterPresenter(fragment: ConverterFragment)
}