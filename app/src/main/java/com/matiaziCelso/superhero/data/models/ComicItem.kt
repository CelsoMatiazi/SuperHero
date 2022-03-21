package com.matiaziCelso.superhero.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ComicItem(
    val title: String,
    val image: String,
    var description: String,
    val value: Double,
    var isFavorite: Boolean,
    val characters: List<CharacterItem>,
    val more: List<ComicItem>
): Parcelable

@Parcelize
data class CharacterItem(
    val name: String,
    val image: String,
    val description: String,
//    val comics: List<ComicItem>
): Parcelable