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
import kotlin.random.Random

class HomeMenuThreeViewModel(
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

    private val _returnedCharacters = MutableLiveData<List<CharacterItem>>()
    val returnedCharacters: MutableLiveData<List<CharacterItem>>
        get() = _returnedCharacters


    init {
        itemsSearch = listasPadrao.comics.asSequence().shuffled().take(1).toList()
    }

    //endregion

    //region Carregar a lista de personagens:
    fun loadMarvelCharacters(offset: Int?){
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchCharacters(offset)
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .collect {
                    val characterList: List<CharacterItem> = it.data.results.map { character ->
                        convertCharacterItem(character)
                    }
                    _returnedCharacters.postValue(characterList.shuffled())
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

    private fun emptyString(txt: String?): String{
        return txt?.ifEmpty {
            noDescription
        } ?: noDescription
        return noDescription
    }

}
