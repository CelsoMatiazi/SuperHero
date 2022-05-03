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
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.ui.adapter.HomeMenuAdapter
import com.matiaziCelso.superhero.ui.detailScreen.ComicDetailActivity
import com.matiaziCelso.superhero.viewModel.HomeMenuTwoViewModel

class MenuTwoFragment : Fragment(R.layout.fragment_home_menu_two){
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: HomeMenuAdapter
    private var comicList = mutableListOf<ComicItem>()
    private val viewModel: HomeMenuTwoViewModel by viewModels()
    private lateinit var homeState: View
    private lateinit var loadingState: View
    private lateinit var bannerState: View
    private lateinit var refreshButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingState = view.findViewById(R.id.home_menu_two_loading)
        homeState = view.findViewById(R.id.home_menu_two_body)
        bannerState = view.findViewById(R.id.home_menu_two_banner)
        refreshButton = view.findViewById(R.id.error_button)

        //region Atribuições Recycler
        recycler = view.findViewById(R.id.home_menu_two_recycler_1)
        adapter = HomeMenuAdapter(){
            sendToDetail(it)
        }
        recycler.adapter = adapter

        if(comicList.isEmpty().not()){
            adapter.updateList(comicList)
        }
        recycler.layoutManager = GridLayoutManager(view.context,3)
        //endregion

        refreshButton.setOnClickListener {
            viewModel.loadMarvelComics(null,(0 until 200).random())
            observer()
        }

        setScrollView()
        observer()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        comicList = adapter.returnList()
    }

    private fun sendToDetail(item: ComicItem) {
        val intent = Intent(context, ComicDetailActivity::class.java)
        intent.putExtra("comicItem", item)
        startActivity(intent)
    }

    private fun observer(){
        viewModel.returnedComics.observe(viewLifecycleOwner){listOfComics ->
            adapter.updateList(listOfComics)
        }
        viewModel.loading.observe(viewLifecycleOwner){
            loadingState.isVisible = it
            homeState.isVisible = !it
        }
        viewModel.error.observe(viewLifecycleOwner){
            bannerState.isVisible = it
        }
    }

    private fun setScrollView(){
        viewModel.loadMarvelComics(null,(0 until 200).random())
        recycler.run{
            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val target = recyclerView.layoutManager as GridLayoutManager?
                    val totalItemCount = target!!.itemCount
                    val lastVisible = target.findLastVisibleItemPosition()
                    val lastItem = lastVisible + 5 >=totalItemCount
                    if(totalItemCount>0 && lastItem && loadingState.isVisible.not()){
                        viewModel.loadMarvelComics(null,(0 until 200).random())
                    }
                }
            })
        }
    }
}