package com.matiaziCelso.superhero.ui.home.search

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.ui.adapter.HomeMenuAdapter
import com.matiaziCelso.superhero.ui.detailScreen.ComicDetailActivity
import com.matiaziCelso.superhero.ui.home.HomeFragment
import com.matiaziCelso.superhero.ui.home.menu_filter.MenuOneFragment
import com.matiaziCelso.superhero.viewModel.SearchViewModel
import java.lang.RuntimeException

class SearchFragment : Fragment(R.layout.fragment_search) {
    private var listener: ISearch? = null
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: HomeMenuAdapter
    private var comicList = mutableListOf<ComicItem>()
    private val viewModel: SearchViewModel by viewModels()
    private lateinit var homeState: View
    private lateinit var loadingState: View
    private lateinit var bannerState: View
    private lateinit var refreshButton: Button
    private lateinit var searchView: SearchView
    private lateinit var emptyIcon: ImageView
    private var offset: Int = 0
    private var newRequest: Boolean = true
    private var totalOfResults: Int = 0
    private var querySearch: String? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ISearch) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + "ISearch não implementado")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingState = view.findViewById(R.id.home_menu_search_loading)
        homeState = view.findViewById(R.id.home_menu_search_body)
        bannerState = view.findViewById(R.id.home_menu_search_banner)
        bannerState.isVisible = comicList.isEmpty().not()
        refreshButton = view.findViewById(R.id.error_button)
        searchView = view.findViewById(R.id.searchView_search)
        emptyIcon = view.findViewById(R.id.search_empty)
        val backBtn = view.findViewById<ImageView>(R.id.search_back_btn)

        //region Atribuições Recycler
        recycler = view.findViewById(R.id.home_menu_search_recycler_1)
        adapter = HomeMenuAdapter(){
            sendToDetail(it)
        }
        recycler.adapter = adapter
        recycler.layoutManager = GridLayoutManager(view.context,3)
        //endregion

        //region Botões
        refreshButton.setOnClickListener {
            offset+=19
            viewModel.loadMarvelComics(querySearch,offset)
            bannerState.isVisible = loadingState.isVisible.not()
            observer()
        }
        backBtn.setOnClickListener {
            listener?.navigateTo(HomeFragment())
        }
        //endregion

        //region Search
        searchView.isIconified = false
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                resetAttributions(query)
                viewModel.loadMarvelComics(query,0)
                observer()
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                resetAttributions(query)
                if(query != ""){
                    viewModel.loadMarvelComics(query,0)
                }
                observer()
                return false
            }

        })
        //endregion

        setScrollView()
        observer()

    }

    private fun sendToDetail(item: ComicItem) {
        val intent = Intent(context, ComicDetailActivity::class.java)
        intent.putExtra("comicItem", item)
        startActivity(intent)
    }

    private fun observer(){
        viewModel.returnedComics.observe(viewLifecycleOwner){listOfComics ->
            adapter.updateList(listOfComics)
            emptyIcon.isVisible = listOfComics.isEmpty()
        }
        viewModel.loading.observe(viewLifecycleOwner){
            loadingState.isVisible = false
        }
        viewModel.error.observe(viewLifecycleOwner){
            bannerState.isVisible = it
        }
        viewModel.newRequestAllowed.observe(viewLifecycleOwner){
            newRequest = it
        }
        viewModel.totalNumberOfComics.observe(viewLifecycleOwner){
            totalOfResults = it
        }
    }

    private fun setScrollView(){
        recycler.run{
            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val target = recyclerView.layoutManager as GridLayoutManager?
                    val totalItemCount = target!!.itemCount
                    val lastVisible = target.findLastVisibleItemPosition()
                    val lastItem = lastVisible + 5 >=totalItemCount
                    if(totalItemCount>0 && lastItem && newRequest && offset< totalOfResults){
                        offset+=19
                        newRequest = newRequest.not()
                        viewModel.loadMarvelComics(querySearch,offset)
                    }
                }
            })
        }
    }

    private fun resetAttributions(query: String?){
        querySearch = query
        offset = 0
        adapter = HomeMenuAdapter(){
            sendToDetail(it)
        }
        recycler.adapter = adapter
    }
}