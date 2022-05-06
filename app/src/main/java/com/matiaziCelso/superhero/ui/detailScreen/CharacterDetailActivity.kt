package com.matiaziCelso.superhero.ui.detailScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.adapter.HomeAdapter
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.viewModel.CharacterDetailViewModel

class CharacterDetailActivity : AppCompatActivity() {

    private val viewModel: CharacterDetailViewModel by viewModels()
    private lateinit var recycler: RecyclerView
    private lateinit var loadingState : View
    private lateinit var homeState : View
    private lateinit var bannerState: View
    private lateinit var refreshButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        supportActionBar?.hide()

        val extras : Bundle? = intent.extras
        val characterItem: CharacterItem? = extras?.getParcelable<CharacterItem>("characterItem")

        val banner = findViewById<ImageView>(R.id.character_detail_banner)
        val backBtn = findViewById<ImageView>(R.id.character_detail_back_btn)
        val name = findViewById<TextView>(R.id.character_detail_title)
        val description = findViewById<TextView>(R.id.character_description)
        bannerState = findViewById(R.id.character_detail_error_banner)
        bannerState.isVisible = false
        refreshButton = findViewById(R.id.error_button)

        //Exibir o loading durante a requisição da API:
        loadingState = findViewById(R.id.characterDetail_loading)
        homeState = findViewById(R.id.characterDetail_screen)


        backBtn.setOnClickListener {
            onBackPressed()
        }
        refreshButton.setOnClickListener {
            characterItem?.id?.let { viewModel.loadCharacterComics(it) }
            bannerState.isVisible = loadingState.isVisible.not()
            observer()
        }

        //Atribuição do personagem na tela:
        Glide.with(banner.context).load(characterItem?.image).into(banner)
        name.text = characterItem?.name
        description.text = characterItem?.description

        //Chamando a requisição do viewModel e aguardando a resposta:
        characterItem?.id?.let { viewModel.loadCharacterComics(it) }
        observer()

        //Atribuição dos comics relacionados ao personagem na recyclerView:
        recycler = findViewById(R.id.character_mais_recycler)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

    }

    private fun sendToComicDetail(item: ComicItem){
        val intent = Intent(this, ComicDetailActivity::class.java)
        intent.putExtra("comicItem", item)
        startActivity(intent)
    }

    private fun observer(){
        viewModel.returnedComics.observe(this){listOfComics ->
            recycler.adapter = HomeAdapter(listOfComics){comic ->
                sendToComicDetail(comic)
            }
        }
        viewModel.loading.observe(this){
            loadingState.isVisible = it
            homeState.isVisible = !it
        }
        viewModel.error.observe(this){
            bannerState.isVisible = it
        }
    }
}