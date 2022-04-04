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
            entity = CompraEntity::class,
            parentColumns = arrayOf("id"),
            childColumns = arrayOf("compra"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    tableName = "carrinho"
)
data class CarrinhoEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "external_id") val externalId: String,
    @ColumnInfo(name = "data_cadastro") val dataCadastro: Date?,
    @ColumnInfo(name = "data_update") val dataUpdate: Date?,
    @ColumnInfo(name = "quadrinho") val quadrinho: String,
    @ColumnInfo(name = "valor") val valor: Double,
    @ColumnInfo(name = "esta_pago") val esta_pago: Boolean,
    @ColumnInfo(name = "compra") val compra: String
)
