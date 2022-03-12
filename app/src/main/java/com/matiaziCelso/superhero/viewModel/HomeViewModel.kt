package com.matiaziCelso.superhero.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.matiaziCelso.superhero.data.mock.ComicsMock
import com.matiaziCelso.superhero.data.models.ComicItem

class HomeViewModel(
    private val repository: ComicsMock = ComicsMock.instance
): ViewModel() {

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


    fun loadComics(){
        _recycler1.value = repository.comics()
        _recycler2.value = repository.avengers()
        _recycler3.value = repository.ironMan()
        _recycler4.value = repository.huck()
        _recycler5.value = repository.thor()
        _recycler6.value = repository.captainAmerica()
    }

}

