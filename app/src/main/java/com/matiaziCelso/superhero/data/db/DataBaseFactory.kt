package com.matiaziCelso.superhero.data.db

import android.content.Context
import androidx.room.Room
import java.lang.IllegalStateException

object DataBaseFactory {
    private var instance: AppDatabase? = null

    @JvmStatic
    fun getAppDataBase() = instance ?: throw IllegalStateException("database isn't initialized")

    fun build(context: Context): AppDatabase {
        val database = Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,"database"
        )
        database.allowMainThreadQueries()
        val appDatabase = database.build()
        instance = appDatabase
        return appDatabase
    }

    @JvmStatic
    fun destroyInstance(){
        instance = null
    }

}