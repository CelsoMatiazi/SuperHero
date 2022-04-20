package com.matiaziCelso.superhero.data

import com.matiaziCelso.superhero.data.db.DataBaseFactory
import com.matiaziCelso.superhero.data.models.ComicItem

object FavItems {

    var items : MutableList<ComicItem> = mutableListOf()

    fun iniciarFavoritos(){
        var database = DataBaseFactory.getAppDataBase()
        var savedList = database.favoritos().getAll().map{
            ComicItem(
                title = "Origins of Marvel Comics",
                value = it.value,
                description = it.description,
                image = it.image,
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf(),
                id = it.id
            )
        }
        items = savedList as MutableList<ComicItem>
    }

}