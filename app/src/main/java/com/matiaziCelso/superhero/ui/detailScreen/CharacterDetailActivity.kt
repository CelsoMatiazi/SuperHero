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
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.viewModel.HomeViewModel

class CharacterDetailActivity : AppCompatActivity() {

    private val repository: ComicsMock = ComicsMock.instance

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


        backBtn.setOnClickListener {
            onBackPressed()
        }

        Glide.with(banner.context).load(characterItem?.image).into(banner)
        name.text = characterItem?.name
        description.text = characterItem?.description


        val recycler = findViewById<RecyclerView>(R.id.character_mais_recycler)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = HomeAdapter(repository.comics()){
            sendToComicDetail(it)
        }
        TODO("Utilizar o characterItem.comics NESTA RECYCLER acima, e fazer o loading na character_detail:")
    }

    private fun sendToComicDetail(item: ComicItem){
        val intent = Intent(this, ComicDetailActivity::class.java)
        intent.putExtra("comicItem", item)
        startActivity(intent)
    }
}