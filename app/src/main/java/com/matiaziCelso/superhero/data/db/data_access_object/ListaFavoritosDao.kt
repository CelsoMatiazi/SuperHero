package com.matiaziCelso.superhero.data.db.data_access_object

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matiaziCelso.superhero.data.db.entities.ListaFavoritosEntity

@Dao
interface ListaFavoritosDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(body: ListaFavoritosEntity)

    @Query("select * from listaFavoritos") //Inserir o nome da tabela desejada.
    fun getAll(): List<ListaFavoritosEntity>
}