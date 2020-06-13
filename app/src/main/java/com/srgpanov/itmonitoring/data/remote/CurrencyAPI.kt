package com.srgpanov.itmonitoring.data.remote

import com.srgpanov.itmonitoring.data.model.ValCurs
import io.reactivex.Single
import retrofit2.http.GET

interface CurrencyAPI {
    @GET("XML_daily.asp")
    fun getCurrency():Single<ValCurs>
}