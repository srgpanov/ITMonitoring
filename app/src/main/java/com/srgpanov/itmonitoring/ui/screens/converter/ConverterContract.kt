package com.srgpanov.itmonitoring.ui.screens.converter

import com.srgpanov.itmonitoring.data.model.Valute
import com.srgpanov.itmonitoring.ui.screens.BasePresenter
import com.srgpanov.itmonitoring.ui.screens.BaseView

interface ConverterContract {

    interface View:BaseView<Presenter>{

        fun showConvertedCurrency(outValue: Float)
        fun restoreState(state: ConvertState)
    }
    interface Presenter: BasePresenter<View>{
        fun onInputValueChanged(value: Float)
        fun getAvailableValuate():List<Valute>
        fun onInputValuteChanged(valute: Valute)
        fun onOutputValuteChanged(valute: Valute)
        fun convertCurrency()
        fun refresh()
    }

}