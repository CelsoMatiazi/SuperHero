package com.matiaziCelso.superhero.data.mock


import com.matiaziCelso.superhero.data.models.CharacterItem

class CharactersMock(
    private val comicsRepository: ComicsMock = ComicsMock.instance
){

    companion object{
        val instance by lazy { CharactersMock() }
    }

}