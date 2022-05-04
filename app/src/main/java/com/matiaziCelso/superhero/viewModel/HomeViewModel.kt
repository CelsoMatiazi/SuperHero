package com.matiaziCelso.superhero.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiaziCelso.superhero.data.Repository.MarvelComicsRepository
import com.matiaziCelso.superhero.data.models.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeViewModel(
    private val marvelRepository: MarvelComicsRepository = MarvelComicsRepository.instance,
): ViewModel() {

    private var loading1 = true
    private var loading2 = true
    private var loading3 = true
    private var loading4 = true
    private var loading5 = true
    private var loading6 = true
    private val noDescription = "TOP SECRET\nA descrição desse comic é confidencial e seu conteudo é conhecido apenas pelo Pentágono e pela SHIELD."
    private var itemsSearch = listOf<String>()

    private val comicsSearchList = listOf(
        "Iron man", "Thor", "X-men", "Avengers", "America", "Wolverine", "Vision",
        "Black Panther", "Deadpool", "Hulk", "Spider-Man",
        "Ant-Man", "Empyre", "Civil War", "Falcon", "Thanos", "Venom", "Strange",
        "Guardians of the Galaxy"
    )


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

    private val _titles = MutableLiveData<List<String>>()
    val titles: MutableLiveData<List<String>>
        get() = _titles


    init {
        itemsSearch = comicsSearchList.asSequence().shuffled().take(6).toList()
    }

//endregion

    //region getComics
    fun getComics1(){
        loadMarvelComics(itemsSearch[0], _recycler1, 1,1)
    }

    fun getComics2(){
        loadMarvelComics(itemsSearch[1], _recycler2, 2,1)
    }

    fun getComics3(){
        loadMarvelComics(itemsSearch[2], _recycler3, 3,1)
    }

    fun getComics4(){
        loadMarvelComics(itemsSearch[3], _recycler4, 4,1)
    }

    fun getComics5(){
        loadMarvelComics(itemsSearch[4], _recycler5, 5,1)
    }

    fun getComics6(){
        loadMarvelComics(itemsSearch[5], _recycler6, 6,1)
    }
    //endregion

    //region Carregar os comics:
    private fun loadMarvelComics(
        comic: String,
        recycler: MutableLiveData<List<ComicItem>>,
        position: Int,
        offset: Int
    ){
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchComics(comic,offset)
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { loadingStatus(position) }
                .collect {
                    val comicConvert: List<ComicItem> = it.data.results.map { comic ->
                        convertComicItem(comic)
                    }
                      _titles.postValue(itemsSearch)
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
            id = comic.id
        )
    }
//endregion

    //region Carregar os dois personagens de destaque:
    fun loadComicCharacters(){
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchCharacters((0 until 200).random())
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .collect {
                    val returnedList = it.data.results.map{
                        convertCharacterItem(it)
                    }.shuffled()
                    val firstCharacter = returnedList[0]
                    val secondCharacter = returnedList[1]
                    _returnedFirstCharacter.postValue(firstCharacter)
                    _returnedSecondCharacter.postValue(secondCharacter)
                    _error.postValue(false)
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

        _loading.postValue(( loading1 ||
                             loading2 ||
                             loading3 ||
                             loading4 ||
                             loading5 ||
                             loading6)
        )

    }
    private fun emptyString(txt: String?): String{
        return txt?.ifEmpty {
            noDescription
        } ?: noDescription
        return noDescription
    }

}

