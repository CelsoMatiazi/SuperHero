package com.matiaziCelso.superhero.data.Repository

import com.matiaziCelso.superhero.data.models.CharacterResponse
import com.matiaziCelso.superhero.data.models.ComicsResponse
import com.matiaziCelso.superhero.data.network.MarvelApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn


class MarvelComicsRepository(private val api: MarvelApi = MarvelApi.instance) {

    fun fetchComics(comic: String?, offset: Int) : Flow<ComicsResponse> = flow {
        emit(api.getMarvelComics(comic,offset))
    }.flowOn(Dispatchers.IO)

    fun fetchCharacters() : Flow<CharacterResponse> = flow {
        emit(api.getMarvelCharacter())
    }.flowOn(Dispatchers.IO)

    fun fetchCharactersById(comicId: Int) : Flow<CharacterResponse> = flow {
        emit(api.getMarvelComicsCharacters(comicId))
    }.flowOn(Dispatchers.IO)

    fun fetchCharacterComics(characterId: Int) : Flow<ComicsResponse> = flow {
        emit(api.getMarvelCharacterComics(characterId))
    }.flowOn(Dispatchers.IO)


    companion object {
        val instance by lazy { MarvelComicsRepository() }
    }
}