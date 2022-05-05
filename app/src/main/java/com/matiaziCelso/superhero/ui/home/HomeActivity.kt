package com.matiaziCelso.superhero.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.matiaziCelso.superhero.R
import androidx.core.content.ContextCompat


class HomeActivity : AppCompatActivity() {

    private lateinit var homeFragment: HomeFragment
    private lateinit var itemFragment: ItemsFragment
    private lateinit var likeFragment: LikeFragment
    private lateinit var cartFragment: CartFragment
    private lateinit var userFragment: UserFragment

    private lateinit var bottomNavigationHome: BottomNavigationView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        this.supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkGrey)


        homeFragment = HomeFragment()
        itemFragment = ItemsFragment()
        likeFragment = LikeFragment()
        cartFragment = CartFragment()
        userFragment = UserFragment()

        setFragment(homeFragment)

        bottomNavigationHome = findViewById(R.id.bottom_nav_home)
        bottomNavigationHome.setOnItemSelectedListener{

            when(it.itemId){
                R.id.botton_home -> setFragment(homeFragment)
                R.id.botton_itens -> setFragment(itemFragment)
                R.id.botton_like -> setFragment(likeFragment)
                R.id.botton_cart -> setFragment(cartFragment)
                R.id.botton_user -> setFragment(userFragment)
            }
            true
        }

    }

    private fun setFragment(fragment: Fragment){

        if(fragment == userFragment){
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.
            setCustomAnimations(
                R.anim.enter_right_to_left,
                R.anim.exit_right_to_left,
            ).
            replace(R.id.fragment_container, fragment)
            fragmentTransaction.commit()

        }else{
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction
                .setCustomAnimations(
                    R.anim.enter_left_to_right,
                    R.anim.exit_left_to_right
                ).
                replace(R.id.fragment_container, fragment)
            fragmentTransaction.commit()
        }
    }

}