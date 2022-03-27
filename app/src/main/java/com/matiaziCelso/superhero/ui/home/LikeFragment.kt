package com.matiaziCelso.superhero.ui.home

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
import com.matiaziCelso.superhero.ui.adapter.FavoriteAdapter
import com.matiaziCelso.superhero.data.FavItems
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.ui.detailScreen.ComicDetailActivity


class LikeFragment : Fragment(R.layout.fragment_like) {

    private lateinit var emptyFav: ImageView
    private lateinit var recycler : RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById<RecyclerView>(R.id.fav_recycle)
        recycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = FavoriteAdapter(FavItems.items,{

            showDialog(it)

        },{
            sendToDetail(it)
        })

        emptyFav = view.findViewById(R.id.fav_empty_text)

        whenItemsIsEmpty()

    }


    private fun whenItemsIsEmpty(){
        emptyFav.isVisible = FavItems.items.isEmpty()
    }

    private fun removeItem(comic: ComicItem){
        FavItems.items.remove(comic)
    }

    private fun showDialog(comic: ComicItem){
        val alertDialog = AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
        alertDialog
            .setTitle("Super Hero")
            .setMessage("Deseja remover esse item dos favoritos?")
            .setCancelable(false)
            .setPositiveButton("Sim") { _, _ ->
                FavItems.items.remove(comic)
                recycler.adapter?.notifyDataSetChanged()
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

}