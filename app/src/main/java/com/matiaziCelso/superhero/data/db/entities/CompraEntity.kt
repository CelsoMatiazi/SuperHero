package com.matiaziCelso.superhero.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "compra"
)
data class CompraEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo(name = "external_id") val externalId: String,
    @ColumnInfo(name = "data_cadastro") val dataCadastro: Date?,
    @ColumnInfo(name = "data_update") val dataUpdate: Date?,
    @ColumnInfo(name = "valor_total") val valorTotal: Double,
    @ColumnInfo(name = "status") val status: String,
    @ColumnInfo(name = "data_compra") val dataCompra: Date?
)
