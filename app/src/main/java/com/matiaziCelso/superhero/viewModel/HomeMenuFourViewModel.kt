package com.matiaziCelso.superhero.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiaziCelso.superhero.data.Repository.MarvelComicsRepository
import com.matiaziCelso.superhero.data.mock.listasPadrao
import com.matiaziCelso.superhero.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeMenuFourViewModel(
    private val marvelRepository: MarvelComicsRepository = MarvelComicsRepository.instance,
): ViewModel() {

    private val noDescription = "TOP SECRET\nA descrição desse comic é confidencial e seu conteudo é conhecido apenas pelo Pentágono e pela SHIELD."
    private var itemsSearch = listOf<String>()


    //region viewModel
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _returnedComics = MutableLiveData<List<ComicItem>>()
    val returnedComics: MutableLiveData<List<ComicItem>>
        get() = _returnedComics

    private val _newRequestAllowed = MutableLiveData<Boolean>()
    val newRequestAllowed: MutableLiveData<Boolean>
        get() = _newRequestAllowed

    private val _totalNumberOfComics = MutableLiveData<Int>()
    val totalNumberOfComics: MutableLiveData<Int>
        get() = _totalNumberOfComics


    init {
        itemsSearch = listasPadrao.comics.asSequence().shuffled().take(1).toList()
    }

    //endregion

    //region Carregar os comics:
    fun loadMarvelComics(comic: String? = null, offset: Int, dateDescriptor: String = "thisMonth"){
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchComics(comic, offset,dateDescriptor)
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .collect {
                    val comicConvert: List<ComicItem> = it.data.results.map { comic ->
                        convertComicItem(comic)
                    }
                    _returnedComics.postValue(comicConvert)
                    _error.postValue(false)
                    _newRequestAllowed.postValue(true)
                    _totalNumberOfComics.postValue(it.data.total)
                }
        }
    }
    private fun convertComicItem(comic: MarvelComic): ComicItem{
        val tempImg = "${comic.images.path}.${comic.images.extension}"

        return ComicItem(
            title = comic.title,
            image = tempImg.replace("http://", "https://"),
            description = comic.description ?: noDescription,
            value = comic.prices[0].price,
            isFavorite = false,
            id = comic.id
        )
    }
//endregion

    private fun emptyString(txt: String?): String{
        return txt?.ifEmpty {
            noDescription
        } ?: noDescription
        return noDescription
    }

}
