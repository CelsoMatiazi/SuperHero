package com.matiaziCelso.superhero.ui.home

import android.content.Intent
import com.matiaziCelso.superhero.ui.adapter.HomeAdapter
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.mock.CharactersMock
import com.matiaziCelso.superhero.data.models.CharacterItem
import com.matiaziCelso.superhero.data.models.ComicItem
import com.matiaziCelso.superhero.data.models.MarvelComic
import com.matiaziCelso.superhero.ui.detailScreen.CharacterDetailActivity
import com.matiaziCelso.superhero.ui.detailScreen.ComicDetailActivity
import com.matiaziCelso.superhero.viewModel.HomeViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var homeMenuFilter : TabLayout
    private lateinit var menuOneFragment: MenuOneFragment
    private lateinit var menuTwoFragment: MenuTwoFragment
    private lateinit var menuThreeFragment: MenuThreeFragment
    private lateinit var menuFourFragment: MenuFourFragment


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        homeMenuFilter = view.findViewById(R.id.home_menu_filter)
        menuOneFragment = MenuOneFragment()
        menuTwoFragment = MenuTwoFragment()
        menuThreeFragment = MenuThreeFragment()
        menuFourFragment = MenuFourFragment()

        setFragment(menuOneFragment)

        homeMenuFilter.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    when(tab.contentDescription){
                        "Home" -> setFragment(menuOneFragment)
                        "Comics" -> setFragment(menuTwoFragment)
                        "Personagens" -> setFragment(menuThreeFragment)
                        "Especiais" -> setFragment(menuFourFragment)
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })

    }


    private fun setFragment(fragment: Fragment){

        val fragmentTransaction = parentFragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.fragment_container_home, fragment)
            fragmentTransaction.commit()

    }

}