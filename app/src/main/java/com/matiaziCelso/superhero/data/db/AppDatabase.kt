package com.matiaziCelso.superhero.data.db

import androidx.room.*
import androidx.room.Database
import com.matiaziCelso.superhero.data.db.data_access_object.*
import com.matiaziCelso.superhero.data.db.entities.*
import java.util.*


@Database(
    entities = [
        ListaFavoritosEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favoritos(): ListaFavoritosDao
}