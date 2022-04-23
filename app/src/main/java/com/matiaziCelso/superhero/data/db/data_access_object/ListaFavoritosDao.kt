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

    @Query("select * from listaFavoritos where listaFavoritos.id = :id") //Inserir o nome da tabela desejada.
    fun getOne(id: Int?): ListaFavoritosEntity

    @Query("select * from listaFavoritos") //Inserir o nome da tabela desejada.
    fun getAll(): List<ListaFavoritosEntity>

    @Query("select count(id) from listaFavoritos where listaFavoritos.id = :id")
    fun comicIsInDatabase(id: Int?): Int

    @Query("""delete from listaFavoritos where listaFavoritos.id = :id""")
    fun delete(id: Int?)
}

//https://kondado.com.br/blog/blog/2020/11/09/be-a-ba-do-sql-filtrando-valores-com-o-where/