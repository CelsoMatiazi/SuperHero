package com.matiaziCelso.superhero.ui.detailScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.adapter.HomeAdapter
import com.matiaziCelso.superhero.data.mock.ComicsMock
import com.matiaziCelso.superhero.data.mock.ComicsMoreMock
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.viewModel.HomeViewModel

class CharacterDetailActivity : AppCompatActivity() {

    private val repository: ComicsMock = ComicsMock.instance
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        supportActionBar?.hide()

        val extras : Bundle? = intent.extras
        val characterItem: CharacterItem? = extras?.getParcelable<CharacterItem>("characterItem")
        val comicItem: ComicItem? = extras?.getParcelable<ComicItem>("comicItem")

        val banner = findViewById<ImageView>(R.id.character_detail_banner)
        val backBtn = findViewById<ImageView>(R.id.character_detail_back_btn)
        val name = findViewById<TextView>(R.id.character_detail_title)
        val description = findViewById<TextView>(R.id.character_description)


        backBtn.setOnClickListener {
            onBackPressed()
        }

        characterItem?.name?.let { comicItem?.id?.let { it1 -> viewModel.loadMarvelCharacter(it1, it) } }
        viewModel.returnedCharacter.observe(this){
            Glide.with(banner.context).load(it.image).into(banner)
            name.text = it.name
            description.text = it.description
        }



        val recycler = findViewById<RecyclerView>(R.id.character_mais_recycler)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = HomeAdapter(repository.comics()){
            sendToComicDetail(it)
        }
    }

    private fun sendToComicDetail(item: ComicItem){
        val intent = Intent(this, ComicDetailActivity::class.java)
        intent.putExtra("comicItem", item)
        startActivity(intent)
    }

    private fun observer(){

    }
}