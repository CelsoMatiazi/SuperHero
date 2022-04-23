package com.matiaziCelso.superhero.ui.home.menu_filter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.ui.adapter.HomeAdapter
import com.matiaziCelso.superhero.ui.detailScreen.ComicDetailActivity
import com.matiaziCelso.superhero.viewModel.HomeMenuTwoViewModel

class MenuTwoFragment : Fragment(R.layout.fragment_home_menu_two) {
    private lateinit var recycler: RecyclerView
    private val viewModel: HomeMenuTwoViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.home_menu_two_recycler_1)
        recycler.layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
        viewModel.loadMarvelComics("Spider-Man")
        observer()

    }

    private fun sendToDetail(item: ComicItem) {
        val intent = Intent(context, ComicDetailActivity::class.java)
        intent.putExtra("comicItem", item)
        startActivity(intent)
    }

    private fun observer(){
        viewModel.returnedComics.observe(viewLifecycleOwner){listOfComics ->
            recycler.adapter = HomeAdapter(listOfComics){comic ->
                sendToDetail(comic)
            }
        }
    }
}