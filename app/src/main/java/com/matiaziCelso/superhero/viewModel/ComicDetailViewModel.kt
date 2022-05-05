package com.matiaziCelso.superhero.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiaziCelso.superhero.data.Repository.MarvelComicsRepository
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.data.models.MarvelCharacter
import com.matiaziCelso.superhero.data.models.MarvelComic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ComicDetailViewModel(private val marvelRepository: MarvelComicsRepository = MarvelComicsRepository.instance) :
    ViewModel() {

    private val noDescription =
        "TOP SECRET\nA descrição desse heroi é confidencial e seu conteudo é conhecido apenas pelo Pentágono e pela SHIELD."
    private var suggestionId: Int = 1009368

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _wasEmpty = MutableLiveData<String>()
    val wasEmpty: LiveData<String>
        get() = _wasEmpty

    private val _returnedCharacters = MutableLiveData<List<CharacterItem>>()
    val returnedCharacters: MutableLiveData<List<CharacterItem>>
        get() = _returnedCharacters

    private val _returnedComics = MutableLiveData<List<ComicItem>>()
    val returnedComics: MutableLiveData<List<ComicItem>>
        get() = _returnedComics


    //region Requisição de personagens
    fun loadComicCharacters(comicId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchCharactersById(comicId)
                .onStart {_loading.postValue(true)}
                .catch { _error.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .collect {
                    val characterConvert: List<CharacterItem> = it.data.results.map { character ->
                        convertCharacterItem(character)
                    }
                    if (characterConvert.isEmpty()) {
                        loadAleatoryCharacters()
                    //Se o comic não tem personagens, chamo essa função que já retorna os personagens e os comics relacionados a estes.
                    }
                    else{
                        _returnedCharacters.postValue(characterConvert)
                        loadMoreComics(characterConvert.shuffled()[0].id)
                        //Se o comic tem os personagens, basta fazer a requisição dos comics relacionados.
                    }
                }
        }
    }

    private fun loadAleatoryCharacters() {
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchCharacters((0 until 1000).random())
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .collect {
                    val aleatoryCharacterConvert: List<CharacterItem> = it.data.results.map { character ->
                        convertCharacterItem(character)
                    }
                    _returnedCharacters.postValue(aleatoryCharacterConvert.shuffled())
                    _wasEmpty.postValue("Conheça também!")
                    suggestionId = aleatoryCharacterConvert.shuffled()[0].id
                    loadMoreComics(suggestionId)
                }
        }
    }

    private fun convertCharacterItem(character: MarvelCharacter): CharacterItem {
        val tempImg = "${character.thumbnail.path}.${character.thumbnail.extension}"

        println("Description")
        println(character.description)
        return CharacterItem(
            id = character.id,
            name = character.name,
            image = tempImg.replace("http://", "https://"),
            description = emptyString(character.description)
        )
    }
    //endregion

    //region Carregar os comics:
    private fun loadMoreComics(characterId: Int){
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchCharacterComics(characterId)
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .collect {
                    val comicConvert: List<ComicItem> = it.data.results.map { comic ->
                        convertComicItem(comic)
                    }

                    if(comicConvert.isEmpty()){
                        loadAleatoryComics()
                    }
                    else{
                        _returnedComics.postValue(comicConvert)
                        _error.postValue(false)
                    }
                }
        }
    }
    private fun loadAleatoryComics(){
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchComics()
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .collect {
                    val comicConvert: List<ComicItem> = it.data.results.map { comic ->
                        convertComicItem(comic)
                    }
                    _returnedComics.postValue(comicConvert)
                    _error.postValue(false)
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


    private fun emptyString(txt: String?): String {
        return txt?.ifEmpty {
            noDescription
        } ?: noDescription
        return noDescription
    }


}