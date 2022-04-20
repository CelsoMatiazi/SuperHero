package com.matiaziCelso.superhero.data.db.data_access_object

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.matiaziCelso.superhero.data.db.entities.CarrinhoEntity

@Dao
interface CarrinhoDao {
    @Query("select * from carrinho")
    fun getAll(): List<CarrinhoEntity>

    @Query("""
        select * from carrinho where
            carrinho.id = :id or
            carrinho.external_id = :external_id
    """)
    fun getOne(id: Int?, external_id: String?): CarrinhoEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun create(vararg body: CarrinhoEntity)

    @Query("""
        delete from carrinho where
            carrinho.id = :id or
            carrinho.external_id = :external_id
    """)
    fun delete(id: Int?, external_id: String?)
}