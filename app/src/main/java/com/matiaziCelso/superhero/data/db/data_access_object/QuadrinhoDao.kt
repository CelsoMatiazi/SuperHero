package com.matiaziCelso.superhero.data.db.data_access_object

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.matiaziCelso.superhero.data.db.entities.QuadrinhoEntity


@Dao
interface QuadrinhoDao {

    @Query("select * from quadrinho")
    fun getAll(): List<QuadrinhoEntity>

    @Query("""
        select * from quadrinho where
            quadrinho.id = :id or
            quadrinho.external_id = :external_id
    """)
    fun getOne(id: Int?, external_id: String?): QuadrinhoEntity

    @Insert
    fun create(body: QuadrinhoEntity)

    @Query("""
        delete from quadrinho where
            quadrinho.id = :id or
            quadrinho.external_id = :external_id
    """)
    fun delete(id: Int?, external_id: String?): Unit
}