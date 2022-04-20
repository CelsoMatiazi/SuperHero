package com.matiaziCelso.superhero.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.matiaziCelso.superhero.data.models.ComicItem

@Entity(tableName = "listaFavoritos")
data class ListaFavoritosEntity(
    @PrimaryKey(autoGenerate = true) val dbId: Int? = null,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "description") val description: String? = null,
    @ColumnInfo(name = "value") val value: Double,
    @ColumnInfo(name = "id") val id: Int,
)