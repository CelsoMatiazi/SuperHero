package com.matiaziCelso.superhero.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiaziCelso.superhero.data.Repository.MarvelComicsRepository
import com.matiaziCelso.superhero.data.mock.ComicsMock
import com.matiaziCelso.superhero.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: ComicsMock = ComicsMock.instance,
    private val marvelRepository: MarvelComicsRepository = MarvelComicsRepository.instance,
): ViewModel() {

    var loading1 = true
    var loading2 = true
    var loading3 = true
    var loading4 = true
    var loading5 = true
    var loading6 = true
    private val noDescription = "TOP SECRET\nA descrição desse comic é confidencial e seu conteudo é conhecido apenas pelo Pentágono e pela SHIELD."


    //region viewModel
    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _recycler1 = MutableLiveData<List<ComicItem>>()
    val recycler1: MutableLiveData<List<ComicItem>>
        get() = _recycler1

    private val _recycler2 = MutableLiveData<List<ComicItem>>()
    val recycler2: MutableLiveData<List<ComicItem>>
        get() = _recycler2

    private val _recycler3 = MutableLiveData<List<ComicItem>>()
    val recycler3: MutableLiveData<List<ComicItem>>
        get() = _recycler3

    private val _recycler4 = MutableLiveData<List<ComicItem>>()
    val recycler4: MutableLiveData<List<ComicItem>>
        get() = _recycler4

    private val _recycler5 = MutableLiveData<List<ComicItem>>()
    val recycler5: MutableLiveData<List<ComicItem>>
        get() = _recycler5

    private val _recycler6 = MutableLiveData<List<ComicItem>>()
    val recycler6: MutableLiveData<List<ComicItem>>
        get() = _recycler6

    private val _returnedFirstCharacter = MutableLiveData<CharacterItem>()
    val returnedFirstCharacter: MutableLiveData<CharacterItem>
        get() = _returnedFirstCharacter

    private val _returnedSecondCharacter = MutableLiveData<CharacterItem>()
    val returnedSecondCharacter: MutableLiveData<CharacterItem>
        get() = _returnedSecondCharacter


//endregion


    //region getComics
    fun getComics1(){
        loadMarvelComics("iron man", _recycler1, 1)
    }

    fun getComics2(){
        loadMarvelComics("avengers", _recycler2, 2)
    }

    fun getComics3(){
        loadMarvelComics("iron man", _recycler3, 3)
    }

    fun getComics4(){
        loadMarvelComics("x-men", _recycler4, 4)
    }

    fun getComics5(){
        loadMarvelComics("thor", _recycler5, 5)
    }

    fun getComics6(){
        loadMarvelComics("america", _recycler6, 6)
    }
    //endregion

    //region Carregar os comics:
    private fun loadMarvelComics(
        comic: String,
        recycler: MutableLiveData<List<ComicItem>>,
        position: Int
    ){
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchComics(comic)
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { loadingStatus(position) }
                .collect {
                    val comicConvert: List<ComicItem> = it.data.results.map { comic ->
                        convertComicItem(comic)
                    }
                    recycler.postValue(comicConvert.shuffled())
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
            characters = listOf<CharacterItem>(),
            more = listOf<ComicItem>(),
            id = comic.id
        )
    }
//endregion

    //region Carregar os dois personagens de destaque:
    fun loadComicCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchCharacters()
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .collect {
                    val firstCharacter = convertCharacterItem(it.data.results[0])
                    val secondCharacter = convertCharacterItem(it.data.results[1])
                    _returnedFirstCharacter.postValue(firstCharacter)
                    _returnedSecondCharacter.postValue(secondCharacter)
                    }
                }
        }

    private fun convertCharacterItem(character: MarvelCharacter): CharacterItem{
        val tempImg = "${character.thumbnail.path}.${character.thumbnail.extension}"

        println("Description")
        println(character.description)
        return CharacterItem(
            id = character.id,
            name = character.name,
            image = tempImg.replace("http://", "https://"),
            description =  emptyString(character.description)
//            comics = character.comics.items
        )
    }
    //endregion

    private fun loadingStatus(position: Int) {

        when(position){
            1 -> loading1 = false
            2 -> loading2 = false
            3 -> loading3 = false
            4 -> loading4 = false
            5 -> loading5 = false
            6 -> loading6 = false
        }

        _loading.postValue(( loading1 &&
                             loading2 &&
                             loading3 &&
                             loading4 &&
                             loading5 &&
                             loading6 && !loading1)
        )

    }
    private fun emptyString(txt: String?): String{
        return txt?.ifEmpty {
            noDescription
        } ?: noDescription
        return noDescription
    }

}

