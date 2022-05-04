package com.matiaziCelso.superhero.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
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
import com.matiaziCelso.superhero.data.CartItems
import com.matiaziCelso.superhero.ui.adapter.ItemsAdapter
import com.matiaziCelso.superhero.data.mock.ItemsPayMock
import com.matiaziCelso.superhero.data.models.BoughtItem
import okhttp3.internal.notifyAll


class ItemsFragment : Fragment(R.layout.fragment_itens) {

    private var firebaseDB = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private lateinit var recycler : RecyclerView
    private lateinit var animation: LottieAnimationView
    private lateinit var emptyItems: ImageView
    var data = mutableListOf<BoughtItem>()
    private lateinit var adapter: ItemsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.items_recycle)
        animation = view.findViewById(R.id.items_anim)
        emptyItems = view.findViewById(R.id.items_isEmpty)

        adapter = ItemsAdapter()

        recycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter

        animation.isVisible = true
        getPurchasesData()
    }

    private fun getPurchasesData(){
        val ref = firebaseDB.getReference(auth.currentUser!!.uid)
        val key = "Purchases"
        ref.child(key).addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val items = snapshot.getValue<HashMap<String, MutableList<BoughtItem>>>()
                data = mutableListOf<BoughtItem>()
                items?.let {
                    it.values.toList().map { purchase ->
                        data.addAll(purchase)
                    }
                    adapter.updateList(data)
                }
                animation.isVisible = false
                emptyItems.isVisible = data.isEmpty()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("ITEMS", "ON CANCELLED -> $error")
            }
        })

    }

}