package com.matiaziCelso.superhero.ui.home

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.airbnb.lottie.LottieAnimationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.adapter.CartItemsAdapter
import com.matiaziCelso.superhero.data.CartItems
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.ui.payment.PaymentActivity

class CartFragment : Fragment(R.layout.fragment_cart) {

    private lateinit var emptyCart: ImageView
    private lateinit var cartValue: TextView
    private lateinit var finalizarBtn: Button
    private lateinit var animation: LottieAnimationView
    private lateinit var recycler : RecyclerView
    private var firebaseDB = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        emptyCart = view.findViewById(R.id.cart_isEmpty)
        finalizarBtn = view.findViewById(R.id.cart_button_finalizar)
        cartValue = view.findViewById(R.id.textView4)
        animation = view.findViewById(R.id.cart_anim)

        recycler = view.findViewById(R.id.cart_recycle)
        recycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)


        finalizarBtn.setOnClickListener {
            val intent = Intent(context, PaymentActivity::class.java)
            startActivity(intent)
        }

        getCartData()

    }


    private fun removeItemFromFirebase(comic: ComicItem){
        val ref = firebaseDB.getReference(auth.currentUser!!.uid)
        val key = "cart"

        ref.child(key).child(comic.id.toString()).removeValue().addOnSuccessListener {
            CartItems.items.remove(comic)
            recycler.adapter?.notifyDataSetChanged()
            whenCartIsEmpty()
            setValue()
        }
        .addOnFailureListener{
            Log.d("CART", "ERRO")
        }
    }


    private fun getCartData(){
        val ref = firebaseDB.getReference(auth.currentUser!!.uid)
        val key = "cart"

        ref.child(key).addValueEventListener(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                val cartItems = snapshot.getValue<HashMap<String, ComicItem>>()
                cartItems?.let {
                    CartItems.items = it.values.toMutableList()
                    animation.isVisible = false
                    recycler.adapter = CartItemsAdapter(CartItems.items){ comic ->
                        showDialog(comic)
                    }
                }
                whenCartIsEmpty()
                setValue()
                animation.isVisible = false
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("CART", "ON CANCELLED -> $error")
            }
        })
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
                removeItemFromFirebase(comic)
            }
            .setNegativeButton("Não") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

}