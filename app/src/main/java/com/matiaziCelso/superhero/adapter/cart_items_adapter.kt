package com.matiaziCelso.superhero.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.models.ComicItem

class CartItemsAdapter(private val items: List<ComicItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        return CartItemViewHolder(
            inflator.inflate(
                R.layout.cart_item_layout,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CartItemViewHolder -> holder.bind(items[position])
        }
    }

    override fun getItemCount(): Int = items.size
}

class CartItemViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val image: ImageView = view.findViewById<ImageView>(R.id.cart_img)
    private val title: TextView = view.findViewById<TextView>(R.id.cart_title)
    private val price: TextView = view.findViewById<TextView>(R.id.cart_item_number)

    fun bind(item: ComicItem){
        Glide.with(image.context).load(item.image).into(image)
        title.text = item.title
        price.text = "R$ ${String.format("%.2f", item.value)}"

    }

}