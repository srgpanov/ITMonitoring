@file:Suppress("DEPRECATION")

package com.srgpanov.itmonitoring.di

import com.srgpanov.itmonitoring.BuildConfig
import com.srgpanov.itmonitoring.data.remote.CurrencyAPI
import com.srgpanov.itmonitoring.data.remote.RemoteDataSource
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {
    companion object{
        private const val currencyBaseUrl ="http://www.cbr.ru/scripts/"
    }

    @Singleton
    @Provides
    fun getInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            loggingInterceptor.apply { level = HttpLoggingInterceptor.Level.BODY }
        }
        return loggingInterceptor;
    }
    @Singleton
    @Provides
    fun getHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
    @Suppress("DEPRECATION")
    @Singleton
    @Provides
    fun getCurrencyApi(httpClient:OkHttpClient):CurrencyAPI{
        return Retrofit.Builder()
            .baseUrl(currencyBaseUrl)
            .client(httpClient)
            .addConverterFactory(
                SimpleXmlConverterFactory.createNonStrict(
                    Persister(AnnotationStrategy())
                )
            )
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(CurrencyAPI::class.java)
    }


}