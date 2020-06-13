package com.srgpanov.itmonitoring.data.remote

import com.srgpanov.itmonitoring.data.model.ValCurs
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class RemoteDataSource @Inject constructor(private val currencyAPI: CurrencyAPI) {
    fun getCurrency(): Single<ValCurs> {
        return currencyAPI.getCurrency()
    }
}