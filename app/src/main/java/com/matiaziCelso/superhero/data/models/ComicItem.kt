package com.matiaziCelso.superhero.data.models

import android.os.Parcelable
import com.matiaziCelso.superhero.data.db.entities.ListaFavoritosEntity
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ComicItem(
    val title: String,
    val image: String,
    var description: String? = null,
    val value: Double,
    var isFavorite: Boolean,
    val id: Int
): Parcelable

@Parcelize
data class CharacterItem(
    val id: Int,
    val name: String,
    val image: String,
    val description: String
): Parcelable