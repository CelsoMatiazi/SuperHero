package com.matiaziCelso.superhero.data.db

import androidx.room.*
import androidx.room.Database
import com.matiaziCelso.superhero.data.db.data_access_object.*
import com.matiaziCelso.superhero.data.db.entities.*
import java.util.*


@Database(
    entities = [
//        QuadrinhoEntity::class,
//        FavoritoEntity::class,
//        QuadrinhoAtuacaoEntity::class,
//        CompraEntity::class,
//        PersonagemEntity::class,
//        CarrinhoEntity::class,
        ListaFavoritosEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
//    abstract fun quadrinho(): QuadrinhoDao
//    abstract fun favorito(): FavoritoDao
//    abstract fun quadrinhoAtuacao(): QuadrinhoAtuacaoDao
//    abstract fun personagem(): PersonagemDao
//    abstract fun carrinho(): CarrinhoDao
//    abstract fun compra(): CompraDao
    abstract fun favoritos(): ListaFavoritosDao
}