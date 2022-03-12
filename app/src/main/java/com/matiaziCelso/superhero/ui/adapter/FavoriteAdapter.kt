package com.matiaziCelso.superhero.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.models.ComicItem


class FavoriteAdapter(
    private val items: List<ComicItem>,
    private val action: (ComicItem) -> Unit,
    private val action2: (ComicItem) -> Unit,
    ): RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        return FavoriteViewHolder(
            inflator.inflate(
                R.layout.fav_item_layout,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FavoriteViewHolder -> holder.favBind(items[position], action, action2)
        }
    }

    override fun getItemCount(): Int = items.size

}

class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val image: ImageView = view.findViewById(R.id.fav_img)
    private val title: TextView = view.findViewById(R.id.fav_title)
    private val number: TextView = view.findViewById(R.id.fav_item_number)
    private val release: TextView = view.findViewById(R.id.fav_item_release)
    private val favIcon: ImageView = view.findViewById(R.id.fav_icon)
    private val itemView: View = view.findViewById(R.id.favorite_view)

    fun favBind(item: ComicItem, action: (ComicItem) -> Unit, action2: (ComicItem) -> Unit){
        Glide.with(image.context).load(item.image).into(image)
        title.text = item.title
        number.text = "numero: #12123"
        release.text = "lançamento: 12/12/12"
        favIcon.setOnClickListener { action.invoke(item) }
        itemView.setOnClickListener { action2.invoke(item) }
    }
}