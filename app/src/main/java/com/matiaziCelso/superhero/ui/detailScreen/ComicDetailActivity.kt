package com.matiaziCelso.superhero.ui.detailScreen

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.net.toUri
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.adapter.CharactersAdapter
import com.matiaziCelso.superhero.ui.adapter.HomeAdapter
import com.matiaziCelso.superhero.data.CartItems
import com.matiaziCelso.superhero.data.db.AppDatabase
import com.matiaziCelso.superhero.data.db.DataBaseFactory
import com.matiaziCelso.superhero.data.db.entities.ListaFavoritosEntity
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.viewModel.ComicDetailViewModel


class ComicDetailActivity : AppCompatActivity() {

    private val viewModel: ComicDetailViewModel by viewModels()
    private lateinit var addCartBtn : TextView
    private lateinit var addCartDoneBtn : FrameLayout
    private lateinit var comicFavIcon : ImageView
    private lateinit var comicShareIcon : ImageView
    private lateinit var recyclerCharacters: RecyclerView
    private lateinit var loadingState : View
    private lateinit var homeState : View
    private lateinit var tagMais: TextView
    private lateinit var tagPersonagens: TextView
    private var database: AppDatabase = DataBaseFactory.getAppDataBase()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comic_detail)
        supportActionBar?.hide()

        //region Inicialização de variáveis
        val extras : Bundle? = intent.extras
        val comicItem: ComicItem? = extras?.getParcelable("comicItem")

        //val banner = findViewById<ImageView>(R.id.comic_detail_banner)
        val backBtn = findViewById<ImageView>(R.id.comic_detail_back_btn)
        val cover = findViewById<ImageView>(R.id.img_item_detail)
        val title = findViewById<TextView>(R.id.comic_detail_title)
        val price = findViewById<TextView>(R.id.comic_detail_price)
        val description = findViewById<TextView>(R.id.comic_description)
        tagMais = findViewById<TextView>(R.id.comic_mais)
        tagPersonagens = findViewById<TextView>(R.id.comic_personagens)
        loadingState = findViewById<View>(R.id.comicDetail_loading)
        homeState = findViewById<View>(R.id.comicDetail_screen)

        recyclerCharacters = findViewById<RecyclerView>(R.id.comic_personagens_recycler)
        recyclerCharacters.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        addCartBtn = findViewById(R.id.comic_add_btn)
        addCartDoneBtn = findViewById(R.id.comic_add_done)
        comicFavIcon = findViewById(R.id.comic_fav_icon)
        comicShareIcon = findViewById(R.id.comic_share_icon)

        //endregion

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

        comicShareIcon.setOnClickListener {
//            val sendIntent: Intent = Intent().apply {
//                action = Intent.ACTION_SEND
//                putExtra(Intent.EXTRA_TEXT,"Deu certo!!")
//                type = "text/plain"
//            }
//            val shareIntent = Intent.createChooser(sendIntent,null)
//            startActivity(shareIntent)

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_STREAM,Uri.parse(comicItem.image))
                type = "image/*"
            }
            startActivity(Intent.createChooser(sendIntent,"Ei! Você já leu ${comicItem.title}?"))
        }

        //Glide.with(banner.context).load(comicItem?.image).into(banner)
        Glide.with(cover.context).load(comicItem.image).into(cover)
        title.text = comicItem.title
        price.text = "R$ ${String.format("%.2f", comicItem.value)}"
        description.text = comicItem.description

        val recycler = findViewById<RecyclerView>(R.id.comic_mais_recycler)
        recycler.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)


        viewModel.loadComicCharacters(comicItem.id)
        observer()
    }


    private fun switchAddToCart(comic: ComicItem){
        if(CartItems.items.filter { it.image == comic.image}.getOrNull(0) != null){
            addCartBtn.isVisible = false
            addCartDoneBtn.isVisible = true
        }
    }

    private fun setFavIcon(comic: ComicItem){
        val lista = database.favoritos().comicIsInDatabase(comic.id) //Por ora este é o "filtro" que consegui implementar.

        if(lista>0){
            comicFavIcon.setImageResource(R.drawable.ic_full_heart)
        }else{
            comicFavIcon.setImageResource(R.drawable.ic_heart_border)
        }
    }

    private fun addFavItem(comic: ComicItem){
        val lista = database.favoritos().comicIsInDatabase(comic.id) //Por ora este é o "filtro" que consegui implementar.

        if(lista>0){
            database.favoritos().delete(comic.id)
        }else{
            val converter = ListaFavoritosEntity(
                title = comic.title,
                image = comic.image,
                description = comic.description,
                value = comic.value,
                id = comic.id
            )
            database.favoritos().create(converter)
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

        viewModel.wasEmpty.observe(this){
            tagPersonagens.text = it
        }

    }
}