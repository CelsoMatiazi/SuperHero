package com.matiaziCelso.superhero.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.adapter.CartItemsAdapter
import com.matiaziCelso.superhero.data.CartItems


class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var emptyText: TextView
    private lateinit var cartValue: TextView
    private lateinit var finalizarBtn: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        emptyText = view.findViewById(R.id.cart_isEmpty)
        finalizarBtn = view.findViewById(R.id.cart_button_finalizar)
        cartValue = view.findViewById(R.id.textView4)


        whenCartIsEmpty()
        setValue()

        val recycler = view.findViewById<RecyclerView>(R.id.cart_recycle)
        recycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        recycler.adapter = CartItemsAdapter(CartItems.items){
            recycler.adapter?.notifyDataSetChanged()
            whenCartIsEmpty()
            setValue()
        }
    }

    private fun whenCartIsEmpty(){

        if(CartItems.items.isEmpty()){
            emptyText.isVisible = true
            finalizarBtn.visibility = View.VISIBLE
        }else{
            emptyText.isVisible = false
            finalizarBtn.visibility = View.GONE

        }
    }

    private fun setValue(){
        var value = 0.0
        if(CartItems.items.isEmpty()){
            cartValue.text = "R$ 0,00"
        }else{
            CartItems.items.map {
                value += it.value
            }

            cartValue.text = "R$ ${String.format("%.2f", value)}"
        }


    }



}