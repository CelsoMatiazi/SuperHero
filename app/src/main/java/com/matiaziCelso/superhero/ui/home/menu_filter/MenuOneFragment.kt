package com.matiaziCelso.superhero.ui.home.menu_filter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.ui.adapter.HomeAdapter
import com.matiaziCelso.superhero.ui.detailScreen.CharacterDetailActivity
import com.matiaziCelso.superhero.ui.detailScreen.ComicDetailActivity
import com.matiaziCelso.superhero.viewModel.HomeViewModel
import com.matiaziCelso.superhero.viewModel.UserViewModel


class MenuOneFragment : Fragment(R.layout.fragment_home_menu) {

    //region Atribuição de variáveis
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var characterOne: CardView
    private lateinit var characterOneTextReceiver: TextView
    private lateinit var characterOneImageReceiver: ImageView
    private lateinit var characterOneReceiver: CharacterItem
    private lateinit var characterTwo: CardView
    private lateinit var characterTwoTextReceiver: TextView
    private lateinit var characterTwoImageReceiver: ImageView
    private lateinit var characterTwoReceiver: CharacterItem

    private lateinit var homeState: View
    private lateinit var loadingState: View
    private lateinit var bannerState: View
    private lateinit var refreshButton: Button

    private lateinit var recycler: RecyclerView
    private lateinit var recycler2: RecyclerView
    private lateinit var recycler3: RecyclerView
    private lateinit var recycler4: RecyclerView
    private lateinit var recycler5: RecyclerView
    private lateinit var recycler6: RecyclerView

    private lateinit var title_0: TextView
    private lateinit var title_1: TextView
    private lateinit var title_2: TextView
    private lateinit var title_3: TextView
    private lateinit var title_4: TextView
    private lateinit var title_5: TextView

    //endregion


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //region Inicialização das variáveis
        characterOne = view.findViewById(R.id.img_persoangem_1)
        characterOneTextReceiver = view.findViewById(R.id.characterOneText)
        characterOneImageReceiver = view.findViewById(R.id.characterOneImage)
        characterTwo = view.findViewById(R.id.img_persoangem_2)
        characterTwoTextReceiver = view.findViewById(R.id.characterTwoText)
        characterTwoImageReceiver = view.findViewById(R.id.characterTwoImage)
        loadingState = view.findViewById(R.id.home_loading)
        homeState = view.findViewById(R.id.home_body)
        bannerState = view.findViewById(R.id.home_menu_one_banner)
        bannerState.isVisible = false
        refreshButton = view.findViewById(R.id.error_button)

        title_0 = view.findViewById(R.id.home_title_0)
        title_1 = view.findViewById(R.id.home_title_1)
        title_2 = view.findViewById(R.id.home_title_2)
        title_3 = view.findViewById(R.id.home_title_3)
        title_4 = view.findViewById(R.id.home_title_4)
        title_5 = view.findViewById(R.id.home_title_5)

        recycler = view.findViewById(R.id.home_recycler_1)
        recycler.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


        recycler2 = view.findViewById(R.id.home_recycler_2)
        recycler2.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


        recycler3 = view.findViewById(R.id.home_recycler_3)
        recycler3.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


        recycler4 = view.findViewById(R.id.home_recycler_4)
        recycler4.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


        recycler5 = view.findViewById(R.id.home_recycler_5)
        recycler5.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)


        recycler6 = view.findViewById(R.id.home_recycler_6)
        recycler6.layoutManager =
            LinearLayoutManager(view.context, LinearLayoutManager.HORIZONTAL, false)

        //endregion

        requisicaoAPI()
        observer()

        refreshButton.setOnClickListener {
            requisicaoAPI()
            observer()
        }

        //region Tornar os personagens em destaque da tela responsivos ao toque:
        characterOne.setOnClickListener {
            if (characterOneReceiver != null) {
                sendToCharacter(characterOneReceiver)
            }
        }

        characterTwo.setOnClickListener {
            if (characterTwoReceiver != null) {
                sendToCharacter(characterTwoReceiver)
            }
        }
        //endregion

    }

    private fun observer() {

        viewModel.error.observe(viewLifecycleOwner) {
            bannerState.isVisible = it
        }

        viewModel.loading.observe(viewLifecycleOwner) {
            loadingState.isVisible = it
            homeState.isVisible = !it
        }

        viewModel.titles.observe(viewLifecycleOwner) {
            title_0.text = it[0]
            title_1.text = it[1]
            title_2.text = it[2]
            title_3.text = it[3]
            title_4.text = it[4]
            title_5.text = it[5]
        }

        viewModel.recycler1.observe(viewLifecycleOwner) { items ->
            recycler.adapter = HomeAdapter(items) {
                sendToDetail(it)
            }
        }

        viewModel.recycler2.observe(viewLifecycleOwner) { items ->
            recycler2.adapter = HomeAdapter(items) {
                sendToDetail(it)
            }
        }

        viewModel.recycler3.observe(viewLifecycleOwner) { items ->
            recycler3.adapter = HomeAdapter(items) {
                sendToDetail(it)
            }
        }

        viewModel.recycler4.observe(viewLifecycleOwner) { items ->
            recycler4.adapter = HomeAdapter(items) {
                sendToDetail(it)
            }
        }

        viewModel.recycler5.observe(viewLifecycleOwner) { items ->
            recycler5.adapter = HomeAdapter(items) {
                sendToDetail(it)
            }
        }

        viewModel.recycler6.observe(viewLifecycleOwner) { items ->
            recycler6.adapter = HomeAdapter(items) {
                sendToDetail(it)
            }
        }
        viewModel.returnedFirstCharacter.observe(viewLifecycleOwner) {
            characterOneReceiver = it
            characterOneTextReceiver.text = it.name
            val urlimge = it.image.replace("http://", "https://")
            Glide.with(this).load(urlimge).into(characterOneImageReceiver)
        }

        viewModel.returnedSecondCharacter.observe(viewLifecycleOwner) {
            characterTwoReceiver = it
            characterTwoTextReceiver.text = it.name
            val urlimge = it.image.replace("http://", "https://")
            Glide.with(this).load(urlimge).into(characterTwoImageReceiver)
        }
    }


    private fun sendToDetail(item: ComicItem) {
        val intent = Intent(context, ComicDetailActivity::class.java)
        intent.putExtra("comicItem", item)
        startActivity(intent)
    }

    private fun sendToCharacter(item: CharacterItem) {
        val intent = Intent(context, CharacterDetailActivity::class.java)
        intent.putExtra("characterItem", item)
        startActivity(intent)
    }

    private fun requisicaoAPI() {
        viewModel.getComics1()
        viewModel.getComics2()
        viewModel.getComics3()
        viewModel.getComics4()
        viewModel.getComics5()
        viewModel.getComics6()
        viewModel.loadComicCharacters()
    }

}
