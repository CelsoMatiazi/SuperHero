package com.matiaziCelso.superhero.ui.detailScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.adapter.CharactersAdapter
import com.matiaziCelso.superhero.ui.adapter.HomeAdapter
import com.matiaziCelso.superhero.data.CartItems
import com.matiaziCelso.superhero.data.FavItems
import com.matiaziCelso.superhero.data.mock.CharactersMock
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.viewModel.ComicDetailViewModel
import com.matiaziCelso.superhero.viewModel.HomeViewModel


class ComicDetailActivity : AppCompatActivity() {

    private val viewModel: ComicDetailViewModel by viewModels()
    private lateinit var addCartBtn : TextView
    private lateinit var addCartDoneBtn : FrameLayout
    private lateinit var comicFavIcon : ImageView
    private lateinit var recyclerCharacters: RecyclerView
    private lateinit var loadingState : View
    private lateinit var homeState : View

    private val charactersRepository: CharactersMock = CharactersMock.instance


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
        loadingState = findViewById<View>(R.id.comicDetail_loading)
        homeState = findViewById<View>(R.id.comicDetail_screen)

        recyclerCharacters = findViewById<RecyclerView>(R.id.comic_personagens_recycler)
        recyclerCharacters.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        addCartBtn = findViewById(R.id.comic_add_btn)
        addCartDoneBtn = findViewById(R.id.comic_add_done)
        comicFavIcon = findViewById(R.id.comic_fav_icon)

        switchAddToCart(comicItem!!)
        setFavIcon(comicItem)


        backBtn.setOnClickListener {
            onBackPressed()
        }

        addCartBtn.setOnClickListener {
            CartItems.items.add(comicItem)
            switchAddToCart(comicItem)
        }

        comicFavIcon.setOnClickListener {
           addFavItem(comicItem)
           setFavIcon(comicItem)
        }

        //Glide.with(banner.context).load(comicItem?.image).into(banner)
        Glide.with(cover.context).load(comicItem.image).into(cover)
        title.text = comicItem.title
        price.text = "R$ ${String.format("%.2f", comicItem.value)}"
        description.text = comicItem.description

        val recycler = findViewById<RecyclerView>(R.id.comic_mais_recycler)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        recycler.adapter = HomeAdapter(comicItem.more){
            sendComicToDetail(it)
        }

        if(comicItem.more.isEmpty()){
            tagMais.text = ""
        }


        viewModel.loadComicCharacters(comicItem.id)
        //Carregamento da lista de personagens -> Id: 43495
//        viewModel.loadComicCharacters(43495)
        observer()
    }


    private fun switchAddToCart(comic: ComicItem){
        if(CartItems.items.filter { it.image == comic.image}.getOrNull(0) != null){
            addCartBtn.isVisible = false
            addCartDoneBtn.isVisible = true
        }
    }

    private fun setFavIcon(comic: ComicItem){
        if(FavItems.items.filter { it.image == comic.image }.getOrNull(0) != null){
            comicFavIcon.setImageResource(R.drawable.ic_full_heart)
        }else{
            comicFavIcon.setImageResource(R.drawable.ic_heart_border)
        }
    }

    private fun addFavItem(comic: ComicItem){
        if(FavItems.items.filter { it.image == comic.image }.getOrNull(0) != null){
            
            FavItems.items.map {
                if(comic.image == it.image){
                    FavItems.items.remove(it)
                }
            }

        }else{
            FavItems.items.add(comic)
        }
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

    private fun observer(){
        viewModel.returnedCharacters.observe(this){listOfCharacter ->
            recyclerCharacters.adapter = CharactersAdapter(listOfCharacter) {character ->
                sendCharacterToDetail(character)
            }
        }
        viewModel.loading.observe(this){
            loadingState.isVisible = it
            homeState.isVisible = !it
        }

    }
}