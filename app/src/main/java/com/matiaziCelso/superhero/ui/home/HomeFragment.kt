package com.matiaziCelso.superhero.ui.home

import android.content.Intent
import com.matiaziCelso.superhero.ui.adapter.HomeAdapter
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.mock.CharactersMock
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.data.models.MarvelComic
import com.matiaziCelso.superhero.ui.detailScreen.CharacterDetailActivity
import com.matiaziCelso.superhero.ui.detailScreen.ComicDetailActivity
import com.matiaziCelso.superhero.viewModel.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val viewModel: HomeViewModel by viewModels()

    private val charactersRepository: CharactersMock = CharactersMock.instance

    private lateinit var characterOne: CardView
    private lateinit var characterTwo: CardView
    private lateinit var loadingState : View
    private lateinit var homeState : View

    private lateinit var recycler: RecyclerView
    private lateinit var recycler2: RecyclerView
    private lateinit var recycler3: RecyclerView
    private lateinit var recycler4: RecyclerView
    private lateinit var recycler5: RecyclerView
    private lateinit var recycler6: RecyclerView



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        characterOne = view.findViewById(R.id.img_persoangem_1)
        characterTwo = view.findViewById(R.id.img_persoangem_2)
        loadingState = view.findViewById(R.id.home_loading)
        homeState = view.findViewById(R.id.home_body)

        recycler = view.findViewById(R.id.home_recycler_1)
        recycler.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


        recycler2 = view.findViewById(R.id.home_recycler_2)
        recycler2.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


        recycler3 = view.findViewById(R.id.home_recycler_3)
        recycler3.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


        recycler4 = view.findViewById(R.id.home_recycler_4)
        recycler4.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


        recycler5 = view.findViewById(R.id.home_recycler_5)
        recycler5.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


        recycler6 = view.findViewById(R.id.home_recycler_6)
        recycler6.layoutManager = LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


        characterOne.setOnClickListener {
            sendToCharacter(charactersRepository.captainAmerica())
        }

        characterTwo.setOnClickListener {
            sendToCharacter(charactersRepository.ironMan())
        }

        //viewModel.loadComics()
        viewModel.getComics1()
        viewModel.getComics2()
        viewModel.getComics3()
        viewModel.getComics4()
        viewModel.getComics5()
        viewModel.getComics6()
        observer()

    }



    private fun observer(){

        viewModel.error.observe(viewLifecycleOwner) {
            if(it){
                Toast.makeText(context, "Deu Erro", Toast.LENGTH_SHORT ).show()
            }
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            loadingState.isVisible = it
            homeState.isVisible = !it
        }

        viewModel.recycler1.observe(viewLifecycleOwner){ items ->
            recycler.adapter = HomeAdapter(items){
                sendToDetail(it)
            }
        }

        viewModel.recycler2.observe(viewLifecycleOwner){items ->
            recycler2.adapter = HomeAdapter(items){
                sendToDetail(it)
            }
        }

        viewModel.recycler3.observe(viewLifecycleOwner){items ->
            recycler3.adapter = HomeAdapter(items){
                sendToDetail(it)
            }
        }

        viewModel.recycler4.observe(viewLifecycleOwner){items ->
            recycler4.adapter = HomeAdapter(items){
                sendToDetail(it)
            }
        }

        viewModel.recycler5.observe(viewLifecycleOwner){items ->
            recycler5.adapter = HomeAdapter(items){
                sendToDetail(it)
            }
        }

        viewModel.recycler6.observe(viewLifecycleOwner){items ->
            recycler6.adapter = HomeAdapter(items){
                sendToDetail(it)
            }
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