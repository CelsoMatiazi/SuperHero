package com.matiaziCelso.superhero.data.Repository

import com.matiaziCelso.superhero.data.models.ComicsResponse
import com.matiaziCelso.superhero.data.network.MarvelApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MarvelComicsRepository(private val api: MarvelApi = MarvelApi.instance) {

    fun fetchComics() : Flow<ComicsResponse> = flow {
        emit(api.getMarvelComics())
    }.flowOn(Dispatchers.IO)


    companion object {
        val instance by lazy { MarvelComicsRepository() }
    }
}