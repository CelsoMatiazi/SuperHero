package com.matiaziCelso.superhero.ui.home.menu_filter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.ui.adapter.HomeMenuAdapter
import com.matiaziCelso.superhero.ui.adapter.HomeMenuAdapterForCharacters
import com.matiaziCelso.superhero.ui.detailScreen.CharacterDetailActivity
import com.matiaziCelso.superhero.ui.detailScreen.ComicDetailActivity
import com.matiaziCelso.superhero.viewModel.HomeMenuThreeViewModel
import com.matiaziCelso.superhero.viewModel.HomeMenuTwoViewModel

class MenuThreeFragment : Fragment(R.layout.fragment_home_menu_three){
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: HomeMenuAdapterForCharacters
    private var characterList = mutableListOf<CharacterItem>()
    private val viewModel: HomeMenuThreeViewModel by viewModels()
    private lateinit var homeState: View
    private lateinit var loadingState: View
    private lateinit var bannerState: View
    private lateinit var refreshButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingState = view.findViewById(R.id.home_menu_three_loading)
        homeState = view.findViewById(R.id.home_menu_three_body)
        bannerState = view.findViewById(R.id.home_menu_three_banner)
        refreshButton = view.findViewById(R.id.error_button)

        //region Atribuições Recycler
        recycler = view.findViewById(R.id.home_menu_three_recycler_1)
        adapter = HomeMenuAdapterForCharacters(){
            sendToDetail(it)
        }
        recycler.adapter = adapter

        if(characterList.isEmpty().not()){
            adapter.updateList(characterList)
        }
        recycler.layoutManager = GridLayoutManager(view.context,3)
        //endregion

        refreshButton.setOnClickListener {
            viewModel.loadMarvelCharacters((0 until 200).random())
            bannerState.isVisible = loadingState.isVisible.not()
            observer()
        }

        setScrollView()
        observer()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        characterList = adapter.returnList()
    }

    private fun sendToDetail(item: CharacterItem) {
        val intent = Intent(context, CharacterDetailActivity::class.java)
        intent.putExtra("characterItem", item)
        startActivity(intent)
    }

    private fun observer(){
        viewModel.returnedCharacters.observe(viewLifecycleOwner){listOfComics ->
            adapter.updateList(listOfComics)
        }
        viewModel.loading.observe(viewLifecycleOwner){
            loadingState.isVisible = it
//            homeState.isVisible = !it
        }
        viewModel.error.observe(viewLifecycleOwner){
            bannerState.isVisible = it
        }
    }

    private fun setScrollView(){
        viewModel.loadMarvelCharacters((0 until 200).random())
        recycler.run{
            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val target = recyclerView.layoutManager as GridLayoutManager?
                    val totalItemCount = target!!.itemCount
                    val lastVisible = target.findLastVisibleItemPosition()
                    val lastItem = lastVisible + 5 >=totalItemCount
                    if(totalItemCount>0 && lastItem && loadingState.isVisible.not()){
                        viewModel.loadMarvelCharacters((0 until 1000).random())
                    }
                }
            })
        }
    }
}