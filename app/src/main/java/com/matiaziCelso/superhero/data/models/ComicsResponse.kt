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
    val images: ComicImage
    //val images: List<ComicImage>
)

data class ComicPrice(val price: Double)
data class ComicImage(val path: String, val extension: String)