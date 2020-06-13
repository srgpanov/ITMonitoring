package com.srgpanov.itmonitoring.ui.screens


interface BasePresenter<T> {
    fun bindView(view: T)


    fun unbindView()
    fun onRelease()
}