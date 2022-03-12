package com.matiaziCelso.superhero.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.adapter.ItemsAdapter
import com.matiaziCelso.superhero.data.mock.ItemsPayMock

class ItemsFragment : Fragment(R.layout.fragment_itens) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.items_recycle)
        recycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = ItemsAdapter(ItemsPayMock().boughtItems())
    }

}