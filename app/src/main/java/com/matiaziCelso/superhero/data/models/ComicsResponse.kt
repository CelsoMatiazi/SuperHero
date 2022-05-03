package com.matiaziCelso.superhero.data.models

import com.google.gson.annotations.SerializedName

//region ComicsResponse
data class ComicsResponse(val data: Results)

data class Results(val offset: Int, val results: List<MarvelComic>)

data class MarvelComic(
    val id: Int,
    val title: String,
    val description: String? = null,
    val prices: List<ComicPrice>,
    @SerializedName("thumbnail")
    val images: ComicImage,
    val characters: CharacterList
    //val images: List<ComicImage>
)

data class ComicPrice(val price: Double)
data class ComicImage(val path: String, val extension: String)
data class CharacterList(val items: List<CharacterSummary>, val available: Int,val collectionURI: String)
data class CharacterSummary(val resourceURI: String, val name: String)
//endregion

//region CharactersResponse
data class CharacterResponse(val data: CharacterResults)
data class CharacterResults(val offset: Int, val results: List<MarvelCharacter>)
data class MarvelCharacter(val id: Int, val name: String, val description: String? = null, val thumbnail: CharacterImage, val comics: CharacterComics)
data class CharacterImage(val path: String, val extension: String)
data class CharacterComics(val items: List<CharacterComicsItems>)
data class CharacterComicsItems(val resourceURI: String, val name: String)
//endregion
