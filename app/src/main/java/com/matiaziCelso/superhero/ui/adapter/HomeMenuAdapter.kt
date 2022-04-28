package com.matiaziCelso.superhero.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.models.ComicItem

class HomeMenuAdapter(private val items: List<ComicItem>, private val action: (comicItem : ComicItem) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflator = LayoutInflater.from(parent.context)

        return MenuItemViewHolder(
            inflator.inflate(
                R.layout.menu_item_front,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MenuItemViewHolder -> holder.bind(items[position], action)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}

class MenuItemViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val image : ImageView = view.findViewById(R.id.menu_item_front)

    fun bind(item: ComicItem, action: (ComicItem) -> Unit){

        val urlImage = item.image.replace("http://", "https://")

        Glide.with(image.context).load(urlImage).into(image)
        image.setOnClickListener { action.invoke(item) }

    }
}