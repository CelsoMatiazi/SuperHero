package com.matiaziCelso.superhero.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.db.AppDatabase
import com.matiaziCelso.superhero.data.db.DataBaseFactory
import com.matiaziCelso.superhero.data.db.entities.CarrinhoEntity
import com.matiaziCelso.superhero.data.db.entities.QuadrinhoEntity

class TesteDB : AppCompatActivity(R.layout.activity_teste_db) {
    private lateinit var dbroom: AppDatabase
    private lateinit var saveButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        saveButton = findViewById(R.id.saveButton)
        saveButton.setOnClickListener {
            save()
        }

        dbroom = DataBaseFactory.getAppDataBase(this)

    }

    override fun onStop() {
        DataBaseFactory.destroyInstance()
        super.onStop()
    }

    private fun save(){
        val comic = QuadrinhoEntity(
            externalId = "1",
            dataCadastro = null,
            dataUpdate = null,
            nome = "Doctor Strange",
            dataLancamento = null,
            descricao = "Deu certo!",
            valor = 22.57
        )

        dbroom.quadrinho().create(comic)
        val savedComics = dbroom.quadrinho().getAll()
        println(savedComics)
    }
}