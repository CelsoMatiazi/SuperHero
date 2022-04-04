package com.matiaziCelso.superhero.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*


@Entity(
    foreignKeys = [
        ForeignKey(
            entity = QuadrinhoEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("quadrinho"),
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = PersonagemEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("personagem"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    tableName = "quadrinho_atuacao"
)
data class QuadrinhoAtuacaoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "external_id") val externalId: String,
    @ColumnInfo(name = "data_cadastro") val dataCadastro: Date?,
    @ColumnInfo(name = "data_update") val dataUpdate: Date?,
    @ColumnInfo(index = true) val quadrinho: String,
    @ColumnInfo(index = true) val personagem: String
)
