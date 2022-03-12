package com.matiaziCelso.superhero.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.models.CharacterItem

class CharactersAdapter(
    private val items: MutableList<CharacterItem>,
    private val action: (character: CharacterItem) -> Unit
    ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflator = LayoutInflater.from(parent.context)

        return CharacterViewHolder(
            inflator.inflate(
                R.layout.personagem_circle_layout,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is CharacterViewHolder -> holder.bind(items[position], action)
        }
    }

    override fun getItemCount(): Int = items.size
}


class CharacterViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val name: TextView = view.findViewById(R.id.character_name)
    private val img: ShapeableImageView = view.findViewById(R.id.character_img)

    fun bind(item: CharacterItem, action: (CharacterItem) -> Unit){
        name.text = item.name
        Glide.with(img.context).load(item.image).into(img)

        img.setOnClickListener { action.invoke(item) }
    }

}