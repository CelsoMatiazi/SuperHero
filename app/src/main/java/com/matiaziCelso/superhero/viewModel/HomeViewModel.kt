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
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import kotlin.random.Random

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

    private val _returnedCharacter = MutableLiveData<CharacterItem>()
    val returnedCharacter : MutableLiveData<CharacterItem>
        get() = _returnedCharacter


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

    //region Comic
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
            description = "${comic.description ?: "Sem descrição"}",
            value = comic.prices[0].price,
            isFavorite = false,
            characters = comic.characters.items.map {
                CharacterItem(it.name,"\"https://www.tenhomaisdiscosqueamigos.com/wp-content/uploads/2019/04/Iron-Man-da-Marvel-696x391.jpg\"","")
            },
            more = listOf<ComicItem>(),
            id = comic.id
        )
    }
    //endregion

    //region Character
    fun loadMarvelCharacter(
        comicId: Int,
        name: String
    ){
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchCharacters(comicId, name)
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { loadingStatus(6) }
                .collect {
                    returnedCharacter.postValue(convertCharacterItem(it.data.results))
                }
        }
    }

    private fun convertCharacterItem(character: Character): CharacterItem{
        val tempImg = "${character.thumbnail.path}.${character.thumbnail.extension}"

        return CharacterItem(
            name = character.name,
            image = tempImg.replace("http://", "https://"),
            description = character.description
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

}

