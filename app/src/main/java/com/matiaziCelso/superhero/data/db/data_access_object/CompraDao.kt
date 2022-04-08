package com.matiaziCelso.superhero.data.db.data_access_object

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.matiaziCelso.superhero.data.db.entities.CompraEntity

@Dao
interface CompraDao {
    @Query("select * from compra")
    fun getAll(): List<CompraEntity>

    @Query("""
        select * from compra where
            compra.id = :id or
            compra.external_id = :external_id
    """)
    fun getOne(id: Int?, external_id: String?): CompraEntity

    @Insert
    fun create(body: CompraEntity)

    @Query("""
        delete from compra where
            compra.id = :id or
            compra.external_id = :external_id
    """)
    fun delete(id: Int?, external_id: String?): Unit
}