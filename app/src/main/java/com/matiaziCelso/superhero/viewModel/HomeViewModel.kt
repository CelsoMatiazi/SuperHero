package com.matiaziCelso.superhero.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matiaziCelso.superhero.data.Repository.MarvelComicsRepository
import com.matiaziCelso.superhero.data.mock.ComicsMock
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.data.models.ComicsResponse
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


    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _recycler1 = MutableLiveData<List<ComicItem>>()
    val recycler1: MutableLiveData<List<ComicItem>>
        get() = _recycler1


//    private val _recycler1 = MutableLiveData<ComicsResponse>()
//    val recycler1: MutableLiveData<ComicsResponse>
//        get() = _recycler1


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


    fun loadComics(){
        _recycler1.value = repository.comics()
        _recycler2.value = repository.avengers()
        _recycler3.value = repository.ironMan()
        _recycler4.value = repository.huck()
        _recycler5.value = repository.thor()
        _recycler6.value = repository.captainAmerica()
    }


    fun loadMarvelComics(){
        viewModelScope.launch(Dispatchers.IO) {
            marvelRepository.fetchComics()
                .onStart { _loading.postValue(true) }
                .catch { _error.postValue(true) }
                .onCompletion { _loading.postValue(false) }
                .collect {
                    val result = it
                    //_recycler1.postValue(result)
                }
        }
    }

}

