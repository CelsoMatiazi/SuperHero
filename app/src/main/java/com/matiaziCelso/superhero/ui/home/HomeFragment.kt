package com.matiaziCelso.superhero.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import com.google.android.material.tabs.TabLayout
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.home.menu_filter.MenuFourFragment
import com.matiaziCelso.superhero.ui.home.menu_filter.MenuOneFragment
import com.matiaziCelso.superhero.ui.home.menu_filter.MenuThreeFragment
import com.matiaziCelso.superhero.ui.home.menu_filter.MenuTwoFragment

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