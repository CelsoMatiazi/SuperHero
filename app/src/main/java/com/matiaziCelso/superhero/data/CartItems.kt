package com.matiaziCelso.superhero.data

import com.google.firebase.database.IgnoreExtraProperties
import com.matiaziCelso.superhero.data.models.ComicItem

@IgnoreExtraProperties
object CartItems {
    var items : MutableList<ComicItem> = mutableListOf()
}