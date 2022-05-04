package com.matiaziCelso.superhero.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem

class HomeMenuAdapterForCharacters(private val action: (characterItem : CharacterItem) -> Unit): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffUtil = AsyncListDiffer(this,DIFF_UTIL)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflator = LayoutInflater.from(parent.context)

        return MenuItemViewHolderForCharacters(
            inflator.inflate(
                R.layout.menu_item_front,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is MenuItemViewHolderForCharacters -> holder.bind(diffUtil.currentList[position],action)
        }
    }

    override fun getItemCount(): Int {
        return diffUtil.currentList.size
    }

    fun updateList(newItems: List<CharacterItem>){
        diffUtil.submitList(diffUtil.currentList.plus(newItems))    //Desse jeito, o Adapter adiciona novos elementos ao final.
    }

    fun returnList(): MutableList<CharacterItem> {
        return diffUtil.currentList
    }

    companion object{
        val DIFF_UTIL = object: DiffUtil.ItemCallback<CharacterItem>(){
            override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean {
                return oldItem == newItem
            }

        }
    }
}

class MenuItemViewHolderForCharacters(view: View): RecyclerView.ViewHolder(view){

    private val image : ImageView = view.findViewById(R.id.menu_item_front)

    fun bind(item: CharacterItem, action: (CharacterItem) -> Unit){

        val urlImage = item.image.replace("http://", "https://")

        Glide.with(image.context).load(urlImage).into(image)
        image.setOnClickListener { action.invoke(item) }

    }
}