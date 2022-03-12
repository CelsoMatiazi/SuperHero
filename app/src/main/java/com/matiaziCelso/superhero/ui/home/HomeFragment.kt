package com.matiaziCelso.superhero.ui.home

import android.content.Intent
import com.matiaziCelso.superhero.mock.ComicsMock
import com.matiaziCelso.superhero.adapter.HomeAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.mock.CharactersMock
import com.matiaziCelso.superhero.mock.ComicsMock.avengers
import com.matiaziCelso.superhero.mock.ComicsMock.captainAmerica
import com.matiaziCelso.superhero.mock.ComicsMock.comics
import com.matiaziCelso.superhero.mock.ComicsMock.huck
import com.matiaziCelso.superhero.mock.ComicsMock.ironMan
import com.matiaziCelso.superhero.mock.ComicsMock.thor
import com.matiaziCelso.superhero.models.CharacterItem
import com.matiaziCelso.superhero.models.ComicItem
import com.matiaziCelso.superhero.ui.detailScreen.CharacterDetailActivity
import com.matiaziCelso.superhero.ui.detailScreen.ComicDetailActivity
import com.matiaziCelso.superhero.ui.loadingScreen.LoadingActivity

class HomeFragment : Fragment(R.layout.fragment_home) {

    lateinit var characterOne: CardView
    lateinit var characterTwo: CardView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterOne = view.findViewById(R.id.img_persoangem_1)
        characterTwo = view.findViewById(R.id.img_persoangem_2)

        val recycler = view.findViewById<RecyclerView>(R.id.home_recycler_1)
        recycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recycler.adapter = HomeAdapter(comics()){
            sendToDetail(it)
        }

        val recycler2 = view.findViewById<RecyclerView>(R.id.home_recycler_2)
        recycler2.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recycler2.adapter = HomeAdapter(avengers()){
            sendToDetail(it)
        }

        val recycler3 = view.findViewById<RecyclerView>(R.id.home_recycler_3)
        recycler3.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recycler3.adapter = HomeAdapter(ironMan()){
            sendToDetail(it)
        }

        val recycler4 = view.findViewById<RecyclerView>(R.id.home_recycler_4)
        recycler4.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recycler4.adapter = HomeAdapter(huck()){
            sendToDetail(it)
        }

        val recycler5 = view.findViewById<RecyclerView>(R.id.home_recycler_5)
        recycler5.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recycler5.adapter = HomeAdapter(thor()){
            sendToDetail(it)
        }

        val recycler6 = view.findViewById<RecyclerView>(R.id.home_recycler_6)
        recycler6.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)
        recycler6.adapter = HomeAdapter(captainAmerica()){
            sendToDetail(it)
        }


        characterOne.setOnClickListener {
            sendToCharacter(CharactersMock.captainAmerica())
        }

        characterTwo.setOnClickListener {
            sendToCharacter(CharactersMock.ironMan())
        }

    }

    private fun sendToDetail(item: ComicItem){
        val intent = Intent(context, ComicDetailActivity::class.java)
        intent.putExtra("comicItem", item)
        startActivity(intent)
    }

    private fun sendToCharacter(item: CharacterItem){
        val intent = Intent(context, CharacterDetailActivity::class.java)
        intent.putExtra("characterItem", item)
        startActivity(intent)
    }

}