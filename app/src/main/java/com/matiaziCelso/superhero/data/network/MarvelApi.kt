package com.matiaziCelso.superhero.data.network

import com.matiaziCelso.superhero.data.factory.GsonFactory
import com.matiaziCelso.superhero.data.factory.OkHttpClientFactory
import com.matiaziCelso.superhero.data.factory.RetrofitFactoryMarvel
import com.matiaziCelso.superhero.data.models.ComicsResponse
import retrofit2.http.GET

interface MarvelApi {

    @GET("comics")
    suspend fun  getMarvelComics() : ComicsResponse

    companion object{
        val instance: MarvelApi by lazy {
            RetrofitFactoryMarvel.build(
                OkHttpClientFactory.build(),
                GsonFactory.build()
            ).create(MarvelApi::class.java)
        }
    }

}