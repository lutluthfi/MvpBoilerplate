package com.lutluthfi.mvpboilerplate.network

import java.util.concurrent.TimeUnit

import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    fun create(baseUrl: String): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(createHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    private fun createHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().apply {
            connectTimeout(NetworkConstant.CONNECTION_TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
            readTimeout(NetworkConstant.CONNECTION_READ_TIME_OUT.toLong(), TimeUnit.MILLISECONDS)
            retryOnConnectionFailure(true)
            connectionPool(ConnectionPool(
                    NetworkConstant.CONNECTION_MAX_IDLE,
                    NetworkConstant.CONNECTION_ALIVE_DURATION.toLong(),
                    TimeUnit.MILLISECONDS))
            addInterceptor(loggingInterceptor)
        }.build()
    }
}// This class is not publicly instantiate
