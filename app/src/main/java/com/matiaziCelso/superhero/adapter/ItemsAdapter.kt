package com.matiaziCelso.superhero.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.models.ItemsItem
import kotlin.math.pow

class ItemsAdapter(private val itens: List<ItemsItem>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflator = LayoutInflater.from(parent.context)
        return ItemViewHolder(
            inflator.inflate(
                R.layout.itens_item_layout,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ItemViewHolder -> holder.bind(itens[position], position, itens.size)
        }
    }

    override fun getItemCount(): Int = itens.size
}

class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){

    private val day: TextView = view.findViewById<TextView>(R.id.box_date_day)
    private val month: TextView = view.findViewById<TextView>(R.id.box_date_month)
    private val title: TextView = view.findViewById<TextView>(R.id.item_title)
    private val status: TextView = view.findViewById<TextView>(R.id.item_status)
    private val price: TextView = view.findViewById<TextView>(R.id.item_price)
    private val barUp: View = view.findViewById<View>(R.id.item_bar_up)
    private val barDown: View = view.findViewById<View>(R.id.item_bar_down)


    fun bind(item: ItemsItem, index: Int, length: Int ){
        day.text = item.day
        month.text = item.month
        title.text = "Titulo: ${item.title}"
        price.text = "R$ ${String.format("%.2f", item.price)}"


        if(index == 0) barUp.isVisible = false
        if(index == length -1) barDown.isVisible = false

        when(item.status){
            -1 -> {
                status.text = status.context.getString(R.string.pagamento_recusado)
                status.setTextColor(Color.RED)
                price.setTextColor(Color.RED)
            }
             0 -> {
                 status.text = status.context.getString(R.string.pagamento_pendente)
                 status.setTextColor(Color.parseColor("#ECAC2B"))
                 price.setTextColor(Color.parseColor("#ECAC2B"))
             }
             1 -> status.text = status.context.getString(R.string.pagamento_aprovado)
        }

    }

}