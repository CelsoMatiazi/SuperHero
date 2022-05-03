package com.matiaziCelso.superhero.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.db.AppDatabase
import com.matiaziCelso.superhero.ui.adapter.FavoriteAdapter
import com.matiaziCelso.superhero.data.db.DataBaseFactory
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.ui.detailScreen.ComicDetailActivity


class LikeFragment : Fragment(R.layout.fragment_like) {

    private lateinit var emptyFav: ImageView
    private lateinit var recycler : RecyclerView
    private var items : List<ComicItem>
    private var database : AppDatabase

    init {
        items = listOf()
        database = DataBaseFactory.getAppDataBase()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        items = iniciarFavoritos()
        recycler = view.findViewById(R.id.fav_recycle)
        recycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = FavoriteAdapter(items,{

            showDialog(it)

        },{
            sendToDetail(it)
        })

        emptyFav = view.findViewById(R.id.fav_empty_text)

        whenItemsIsEmpty()

    }


    private fun whenItemsIsEmpty(){
        emptyFav.isVisible = items.isEmpty()
    }

    private fun removeItem(id: Int){
        database.favoritos().delete(id)
        items = iniciarFavoritos()
    }

    //@SuppressLint("NotifyDataSetChanged")
    private fun showDialog(id: Int){
        val alertDialog = AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
        alertDialog
            .setTitle("Super Hero")
            .setMessage("Deseja remover esse item dos favoritos?")
            .setCancelable(false)
            .setPositiveButton("Sim") { _, _ ->
                removeItem(id)
//                recycler.adapter?.notifyDataSetChanged()
                recycler.adapter = FavoriteAdapter(items,{ showDialog(it) },{ sendToDetail(it) })
                whenItemsIsEmpty()
            }
            .setNegativeButton("Não") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    private fun sendToDetail(item: ComicItem){
        val intent = Intent(context, ComicDetailActivity::class.java)
        intent.putExtra("comicItem", item)
        startActivity(intent)
    }

    fun iniciarFavoritos(): MutableList<ComicItem>{

        val savedList = database.favoritos().getAll().map{
            ComicItem(
                title = it.title,
                value = it.value,
                description = it.description,
                image = it.image,
                isFavorite = false,
                more = mutableListOf(),
                characters = mutableListOf(),
                id = it.id
            )
        }
        return savedList as MutableList<ComicItem>
    }

}