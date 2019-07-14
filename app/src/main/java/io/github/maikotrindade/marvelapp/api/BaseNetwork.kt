package io.github.maikotrindade.marvelapp.api

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

abstract class BaseNetwork {

    val gson: Gson
    val okHttpClient: OkHttpClient

    init {
        gson = createGson()

        okHttpClient = createHttpClient()
    }

    private fun createGson(): Gson {
        return GsonBuilder().create()
    }

    private fun createHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        return OkHttpClient().newBuilder()
            .addInterceptor(httpLoggingInterceptor.apply {
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }
}