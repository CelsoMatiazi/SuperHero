package com.matiaziCelso.superhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.db.AppDatabase
import com.matiaziCelso.superhero.data.db.DataBaseFactory
import com.matiaziCelso.superhero.data.db.entities.CarrinhoEntity
import com.matiaziCelso.superhero.data.db.entities.FavoritoEntity
import com.matiaziCelso.superhero.data.db.entities.ListaFavoritosEntity
import com.matiaziCelso.superhero.data.db.entities.QuadrinhoEntity
import com.matiaziCelso.superhero.data.models.ComicItem

class TesteDB : AppCompatActivity(R.layout.activity_teste_db) {
    private lateinit var dbroom: AppDatabase
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        saveButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            save()
        }

        dbroom = DataBaseFactory.getAppDataBase()

    }

    override fun onStop() {
        DataBaseFactory.destroyInstance()
        super.onStop()
    }

    private fun save(){
        val comic = ComicItem(
            title = "Origins of Marvel Comics",
            value = 19.99,
            image = "https://m.media-amazon.com/images/I/61KFLylOgPL.jpg",
            isFavorite = false,
            more = mutableListOf(),
            characters = mutableListOf(),
            id=1
        )

//        dbroom.favoritos().create(ListaFavoritosEntity(comic = "comic"))
//        val savedComics = dbroom.favoritos().getAll()
//        println(savedComics)
    }
}