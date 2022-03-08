package com.matiaziCelso.superhero.ui.detailScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.adapter.CharactersAdapter
import com.matiaziCelso.superhero.adapter.HomeAdapter
import com.matiaziCelso.superhero.data.CartItems
import com.matiaziCelso.superhero.data.FavItems
import com.matiaziCelso.superhero.mock.CharactersMock
import com.matiaziCelso.superhero.models.CharacterItem
import com.matiaziCelso.superhero.models.ComicItem

class ComicDetailActivity : AppCompatActivity() {

    private lateinit var addCartBtn : TextView
    private lateinit var addCartDoneBtn : FrameLayout
    private lateinit var comicFavIcon : ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)
        supportActionBar?.hide()

        val extras : Bundle? = intent.extras
        val comicItem: ComicItem? = extras?.getParcelable("comicItem")

        //val banner = findViewById<ImageView>(R.id.comic_detail_banner)
        val backBtn = findViewById<ImageView>(R.id.comic_detail_back_btn)
        val cover = findViewById<ImageView>(R.id.img_item_detail)
        val title = findViewById<TextView>(R.id.comic_detail_title)
        val price = findViewById<TextView>(R.id.comic_detail_price)
        val description = findViewById<TextView>(R.id.comic_description)
        val tagMais = findViewById<TextView>(R.id.comic_mais)
        addCartBtn = findViewById(R.id.comic_add_btn)
        addCartDoneBtn = findViewById(R.id.comic_add_done)
        comicFavIcon = findViewById(R.id.comic_fav_icon)

        switchAddToCart(comicItem!!)
        setFavIcon(comicItem, false)


        backBtn.setOnClickListener {
            onBackPressed()
        }

        addCartBtn.setOnClickListener {
            CartItems.items.add(comicItem)
            switchAddToCart(comicItem)
        }

        comicFavIcon.setOnClickListener {
           addFavItem(comicItem)
           setFavIcon(comicItem, true)
        }

        //Glide.with(banner.context).load(comicItem?.image).into(banner)
        Glide.with(cover.context).load(comicItem.image).into(cover)
        title.text = comicItem.title
        price.text = "R$ " + comicItem.value.toString()
        description.text = comicItem.description

        val recycler = findViewById<RecyclerView>(R.id.comic_mais_recycler)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recycler.adapter = HomeAdapter(comicItem.more){
            sendComicToDetail(it)
        }
        if(comicItem.more.isEmpty()){
            tagMais.text = ""
        }


        val recyclerCharacters = findViewById<RecyclerView>(R.id.comic_personagens_recycler)

        val characters =  mutableListOf(
            CharactersMock.ironMan(),
            CharactersMock.thor(),
            CharactersMock.huck(),
            CharactersMock.captainAmerica(),
        ).shuffled()

        recyclerCharacters.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerCharacters.adapter = CharactersAdapter(characters as MutableList<CharacterItem>){
            sendCharacterToDetail(it)
        }

    }



    private fun switchAddToCart(comic: ComicItem){
        println("ENTROUUUUUUU")
        println(CartItems.items.size)
        if(CartItems.items.contains(comic)){
            println("CONTEMMMMMMMM")
            addCartBtn.isVisible = false
            addCartDoneBtn.isVisible = true
        }
    }

    private fun setFavIcon(comic: ComicItem , changeStatus: Boolean){
        if(comic.isFavorite || FavItems.items.contains(comic) || changeStatus ){
            comicFavIcon.setImageResource(R.drawable.ic_full_heart)
        }else{
            comicFavIcon.setImageResource(R.drawable.ic_heart_border)
        }


    }

    private fun addFavItem(comic: ComicItem){
        FavItems.items.add(comic)
    }

    private fun sendComicToDetail(item: ComicItem){
        val intent = Intent(this, ComicDetailActivity::class.java)
        intent.putExtra("comicItem", item)
        startActivity(intent)
    }


    private fun sendCharacterToDetail(item: CharacterItem){
        val intent = Intent(this, CharacterDetailActivity::class.java)
        intent.putExtra("characterItem", item)
        startActivity(intent)
    }
}