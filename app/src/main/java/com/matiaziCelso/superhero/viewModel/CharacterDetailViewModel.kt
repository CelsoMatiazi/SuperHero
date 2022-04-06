package com.matiaziCelso.superhero.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiaziCelso.superhero.data.Repository.MarvelComicsRepository
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.data.models.MarvelComic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class CharacterDetailViewModel(private val marvelRepository: MarvelComicsRepository = MarvelComicsRepository.instance): ViewModel() {

    private val noDescription = "TOP SECRET\nA descrição desse comic é confidencial e seu conteudo é conhecido apenas pelo Pentágono e pela SHIELD."


    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _returnedComics = MutableLiveData<List<ComicItem>>()
    val returnedComics: MutableLiveData<List<ComicItem>>
        get() = _returnedComics


    fun loadCharacterComics(characterId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchCharacterComics(characterId)
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { _loading.postValue(false)}
                .collect {
                    val comicConvert: List<ComicItem> = it.data.results.map { comic ->
                        convertComicItem(comic)
                    }
                    _returnedComics.postValue(comicConvert)
                }
        }
    }
    private fun convertComicItem(comic: MarvelComic): ComicItem {
        val tempImg = "${comic.images.path}.${comic.images.extension}"

        return ComicItem(
            title = comic.title,
            image = tempImg.replace("http://", "https://"),
            description = comic.description ?: noDescription,
            value = comic.prices[0].price,
            isFavorite = false,
            characters = listOf<CharacterItem>(),
            more = listOf<ComicItem>(),
            id = comic.id
        )
    }
}