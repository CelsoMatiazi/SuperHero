package com.matiaziCelso.superhero.data.models

import com.google.gson.annotations.SerializedName

data class ComicsResponse(val data: Results)

data class Results(val results: List<MarvelComic>)

data class MarvelComic(
    val id: Int,
    val title: String,
    val description: String,
    val prices: List<ComicPrice>,
    @SerializedName("thumbnail")
    val images: ComicImage,
    val characters: CharacterList
    //val images: List<ComicImage>
)

data class ComicPrice(val price: Double)
data class ComicImage(val path: String, val extension: String)
data class CharacterList(val items: List<CharacterSummary>, val available: Int)
data class CharacterSummary(val resourceURI: String, val name: String)