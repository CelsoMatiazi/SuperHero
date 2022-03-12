package com.matiaziCelso.superhero.ui.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.adapter.CartItemsAdapter
import com.matiaziCelso.superhero.data.CartItems
import com.matiaziCelso.superhero.models.ComicItem


class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var emptyCart: ImageView
    private lateinit var cartValue: TextView
    private lateinit var finalizarBtn: Button
    private lateinit var recycler : RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emptyCart = view.findViewById(R.id.cart_isEmpty)
        finalizarBtn = view.findViewById(R.id.cart_button_finalizar)
        cartValue = view.findViewById(R.id.textView4)

        whenCartIsEmpty()
        setValue()

        recycler = view.findViewById<RecyclerView>(R.id.cart_recycle)
        recycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)

        recycler.adapter = CartItemsAdapter(CartItems.items){
            //CartItems.items.remove(it)
            showDialog(it)
            setValue()

        }
    }

    private fun whenCartIsEmpty(){
        if(CartItems.items.isEmpty()){
            emptyCart.isVisible = true
            finalizarBtn.visibility = View.GONE
        }else{
            emptyCart.isVisible = false
            finalizarBtn.visibility = View.VISIBLE
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


    private fun showDialog(comic: ComicItem){
        val alertDialog = AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
        alertDialog
            .setTitle("Super Hero")
            .setMessage("Deseja remover esse item do carrinho?")
            .setCancelable(false)
            .setPositiveButton("Sim") { _, _ ->
                CartItems.items.remove(comic)
                recycler.adapter?.notifyDataSetChanged()
                whenCartIsEmpty()
                setValue()
            }
            .setNegativeButton("Não") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

}