package com.matiaziCelso.superhero.data.mock

import java.util.*


object listasPadrao {
    val comics = listOf(
        "Iron man", "Thor", "X-men", "Avengers", "America", "Wolverine", "Vision",
        "Black Panther", "Deadpool", "Hulk", "Spider-Man",
        "Ant-Man", "Empyre", "Civil War", "Falcon", "Thanos", "Venom", "Strange",
        "Guardians of the Galaxy"
    )
    fun aleatorizarComic(): String =  comics[(comics.indices).random()]

}