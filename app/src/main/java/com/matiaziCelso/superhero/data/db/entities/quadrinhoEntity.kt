package com.matiaziCelso.superhero.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "quadrinho")
data class quadrinhoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "external_id")
    val externalId: String,

    @ColumnInfo(name = "nome")
    val nome: String,

    @ColumnInfo(name = "data_lancamento")
    val dataLancamento: Date,

    @ColumnInfo(name = "descricao")
    val descricao: String,

    @ColumnInfo(name = "valor")
    val valor: Float,

    @ColumnInfo(name = "data_cadastro")
    val dataCadastro: Date,

    @ColumnInfo(name = "data_update")
    val dataUpdate: Date,
)
