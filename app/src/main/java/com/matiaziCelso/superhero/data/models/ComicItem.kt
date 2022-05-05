package com.matiaziCelso.superhero.data.models

import android.os.Parcelable
import com.google.firebase.database.IgnoreExtraProperties
import com.matiaziCelso.superhero.data.db.entities.ListaFavoritosEntity
import kotlinx.android.parcel.Parcelize

@IgnoreExtraProperties
@Parcelize
data class ComicItem(
    val title: String = "",
    val image: String = "",
    var description: String? = null,
    val value: Double = 0.0,
    var isFavorite: Boolean = false,
    val characters: List<CharacterItem> = listOf(),
    val more: List<ComicItem> = listOf(),
    val id: Int = 0
): Parcelable

@IgnoreExtraProperties
@Parcelize
data class CharacterItem(
    val id: Int = 0,
    val name: String = "",
    val image: String = "",
    val description: String = ""
): Parcelable