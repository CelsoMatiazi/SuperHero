package com.matiaziCelso.superhero.data.network

import com.matiaziCelso.superhero.data.factory.GsonFactory
import com.matiaziCelso.superhero.data.factory.OkHttpClientFactory
import com.matiaziCelso.superhero.data.factory.RetrofitFactoryMarvel
import com.matiaziCelso.superhero.data.models.ComicsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryName

interface MarvelApi {

    @GET("comics")
    suspend fun  getMarvelComics(@Query("title") comic :String) : ComicsResponse


    companion object{
        val instance: MarvelApi by lazy {
            RetrofitFactoryMarvel.build(
                OkHttpClientFactory.build(),
                GsonFactory.build()
            ).create(MarvelApi::class.java)
        }
    }

}