package com.matiaziCelso.superhero.data.db.data_access_object

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.matiaziCelso.superhero.data.db.entities.QuadrinhoAtuacaoEntity
import com.matiaziCelso.superhero.data.db.entities.QuadrinhoEntity


@Dao
interface QuadrinhoAtuacaoDao {
    @Query("select * from quadrinho_atuacao")
    fun getAll(): List<QuadrinhoAtuacaoEntity>

    @Query("""
        select * from quadrinho_atuacao where
            quadrinho_atuacao.id = :id or
            quadrinho_atuacao.external_id = :external_id
    """)
    fun getOne(id: Int?, external_id: String?): QuadrinhoAtuacaoEntity

    @Insert
    fun create(body: QuadrinhoAtuacaoEntity)

    @Query("""
        delete from quadrinho_atuacao where
            quadrinho_atuacao.id = :id or
            quadrinho_atuacao.external_id = :external_id
    """)
    fun delete(id: Int?, external_id: String?): Unit
}