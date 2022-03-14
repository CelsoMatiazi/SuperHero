package com.matiaziCelso.superhero.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.models.ComicItem



class HomeAdapter(
    private val items: List<ComicItem>,
    private val action: (comicItem : ComicItem) -> Unit
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>()  {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflator = LayoutInflater.from(parent.context)

        return ComicViewHolder(
            inflator.inflate(
                R.layout.comic_front,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ComicViewHolder -> holder.bind(items[position], action)
        }
    }


    override fun getItemCount(): Int = items.size

}



class ComicViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val image : ImageView = view.findViewById(R.id.comic_front)

    val urlimge = "http://i.annihil.us/u/prod/marvel/i/mg/3/30/622a29d47901c.jpg"

    fun bind(item: ComicItem, action: (ComicItem) -> Unit){
        Glide.with(image.context).load(item.image).into(image)
        image.setOnClickListener { action.invoke(item) }

    }
}



