package com.matiaziCelso.superhero.ui.home.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.matiaziCelso.superhero.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

/*
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
import java.util.*

class MenuTwoFragment : Fragment(R.layout.fragment_home_menu_two) {
    private lateinit var recycler: RecyclerView
    private lateinit var adapter: HomeMenuAdapter
    private lateinit var comicAleatorio: String
    private var comicList = mutableListOf<ComicItem>()
    private val viewModel: HomeMenuTwoViewModel by viewModels()
    private var offset = 0

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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

//        val comicAleatorio = listasPadrao.comics.asSequence().shuffled().take(2).toList()
        comicAleatorio = listasPadrao.comics.asSequence().shuffled().toList()[listasPadrao.comics.size*Random().nextInt()]

        //TODO("Inicializar um comic aleatório, e combinar diversos resultados de comics")
        viewModel.loadMarvelComics(comicAleatorio,0)
        //endregion

        setScrollView()
        observer()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        comicList = adapter.returnList()
//        Toast.makeText(context,"Entrei aqui",Toast.LENGTH_LONG).show()
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
                    val target = recyclerView.layoutManager as GridLayoutManager?
                    val totalItemCount = target!!.itemCount
                    val lastVisible = target.findLastVisibleItemPosition()
                    val lastItem = lastVisible + 5 >=totalItemCount
//                    Toast.makeText(view?.context,totalItemCount.toString(),Toast.LENGTH_SHORT).show()
                    if(totalItemCount>0 && lastItem){
                        offset += 18
                        viewModel.loadMarvelComics(comicAleatorio,offset)
                    }
                }
            })
        }
    }
}
/*
if(totalItemCount>0 && lastItem &&loader.isvisible.not()) -> Colocar no observer o loading
search.setOnQueryTextListener(object: SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(query: String?): Boolean {
                TODO("Not yet implemented")
            }

        })
 */
 */