package com.srgpanov.itmonitoring.ui.screens.converter

import com.srgpanov.itmonitoring.data.model.Valute

class ConvertState(
    val inValue:Float,
    val outValue:Float,
    val inCurrency:Valute,
    val outCurrency:Valute,
    val dataFromLocal:Boolean
) {
}