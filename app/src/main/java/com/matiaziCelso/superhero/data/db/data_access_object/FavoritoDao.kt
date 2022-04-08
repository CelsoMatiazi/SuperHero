package com.matiaziCelso.superhero.data.db.data_access_object

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.matiaziCelso.superhero.data.db.entities.FavoritoEntity

@Dao
interface FavoritoDao {

    @Query("select * from favorito")
    fun getAll(): List<FavoritoEntity>

    @Query("""
        select * from favorito where
            favorito.id = :id or
            favorito.external_id = :external_id
    """)
    fun getOne(id: Int?, external_id: String?): FavoritoEntity

    @Insert
    fun create(body: FavoritoEntity)

    @Query("""
        delete from favorito where
            favorito.id = :id or
            favorito.external_id = :external_id
    """)
    fun delete(id: Int?, external_id: String?): Unit
}