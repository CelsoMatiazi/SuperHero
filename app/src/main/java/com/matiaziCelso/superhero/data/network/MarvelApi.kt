package com.matiaziCelso.superhero.data.network

import com.matiaziCelso.superhero.data.factory.GsonFactory
import com.matiaziCelso.superhero.data.factory.OkHttpClientFactory
import com.matiaziCelso.superhero.data.factory.RetrofitFactoryMarvel
import com.matiaziCelso.superhero.data.models.CharacterResponse
import com.matiaziCelso.superhero.data.models.ComicsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryName

interface MarvelApi {

    @GET("comics")
    suspend fun  getMarvelComics(@Query("title") comic :String) : ComicsResponse
    @GET("comics/{comicId}/characters")
    suspend fun getMarvelComicsCharacters(@Path("comicId") comicId: Int, @Query("name") name: String): CharacterResponse

    companion object{
        val instance: MarvelApi by lazy {
            RetrofitFactoryMarvel.build(
                OkHttpClientFactory.build(),
                GsonFactory.build()
            ).create(MarvelApi::class.java)
        }
    }

}