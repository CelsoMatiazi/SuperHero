package com.matiaziCelso.superhero.data.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class QueryInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val requestUrl = original.url

        val url = requestUrl.newBuilder()
            .addQueryParameter("ts","1630864899194")
            .addQueryParameter("apikey", "ec5a2ce55642448dc7efe46d0dcde46b")
            .addQueryParameter("hash", "df70db149a157c283d014c6ffa1c40c0")
            .addQueryParameter("limit", "40")
            .build()


        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}