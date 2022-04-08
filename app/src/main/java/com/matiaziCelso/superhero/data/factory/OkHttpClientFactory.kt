package com.matiaziCelso.superhero.data.factory

import com.matiaziCelso.superhero.BuildConfig
import com.matiaziCelso.superhero.data.interceptor.QueryInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object OkHttpClientFactory {

    fun build() : OkHttpClient = OkHttpClient.Builder().apply {

        addInterceptor(QueryInterceptor())

        if(BuildConfig.DEBUG){
            val loginInterceptor = HttpLoggingInterceptor()
            loginInterceptor.level = HttpLoggingInterceptor.Level.BODY
            addInterceptor(loginInterceptor)
        }

        readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
        connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)

    }.build()

    private const val DEFAULT_TIMEOUT = 60L
}