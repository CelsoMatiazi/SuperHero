package com.matiaziCelso.superhero.ui.home.menu_filter

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.mock.listasPadrao
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.ui.adapter.HomeAdapter
import com.matiaziCelso.superhero.ui.adapter.HomeMenuAdapter
import com.matiaziCelso.superhero.ui.detailScreen.ComicDetailActivity
import com.matiaziCelso.superhero.viewModel.HomeMenuTwoViewModel

class MenuTwoFragment : Fragment(R.layout.fragment_home_menu_two) {
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: HomeMenuAdapter
    private var comicList = mutableListOf<ComicItem>()
    private val viewModel: HomeMenuTwoViewModel by viewModels()
    private var offset = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //region Atribuições Recycler
        recycler = view.findViewById(R.id.home_menu_two_recycler_1)
        adapter = HomeMenuAdapter()
        recycler.adapter = adapter
        if(comicList.isEmpty().not()){
            adapter.updateList(comicList)
        }
        recycler.layoutManager = GridLayoutManager(view.context,3)

        val comicAleatorio = listasPadrao.comics.asSequence().shuffled().take(2).toList()

        //TODO("Inicializar um comic aleatório, e combinar diversos resultados de comics")
        viewModel.loadMarvelComics("Spider-Man",0)
        //endregion

        setScrollView()
        observer()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        comicList = adapter.returnList()
        Toast.makeText(context,"Entrei aqui",Toast.LENGTH_LONG).show()
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
    }

    private fun setScrollView(){

        recycler.run{
            addOnScrollListener(object: RecyclerView.OnScrollListener(){
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
//                    val target = recyclerView.layoutManager as LinearLayoutManager?
                    val target = recyclerView.layoutManager as GridLayoutManager?
                    val totalItemCount = target!!.itemCount
                    val lastVisible = target.findLastVisibleItemPosition()
                    val lastItem = lastVisible + 5 >=totalItemCount
                    if(totalItemCount>0 && lastItem && offset<40){
                        offset += 18
                        viewModel.loadMarvelComics("Spider-Man",offset)
                        Toast.makeText(view?.context,totalItemCount.toString(),Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}
/*
if(totalItemCount>0 && lastItem &&loader.isvisible.not()) -> Colocar no observer o loading
 */