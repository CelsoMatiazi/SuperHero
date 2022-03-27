package com.matiaziCelso.superhero.data.db.data_access_object

import androidx.room.Dao
import androidx.room.Query
import com.matiaziCelso.superhero.data.db.entities.quadrinhoEntity

@Dao
interface quadrinhoDao {

    @Query("select * from quadrinho")
    fun getAll(): List<quadrinhoEntity>

    @Query("select * from quadrinho where quadrinho.id = :quadrinho_id or quadrinho.external_id = :quadrinho_external_id")
    fun getOne(quadrinho_id: Int?, quadrinho_external_id: String?): quadrinhoEntity


}