package com.matiaziCelso.superhero.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.home.HomeActivity
import com.matiaziCelso.superhero.ui.loadingScreen.LoadingActivity


class LoginFragment : Fragment(R.layout.fragment_login) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val register : TextView = view.findViewById(R.id.txt_cadastrar)
        val login : TextView = view.findViewById(R.id.login_entrar_btn)

        register.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.login_frag_container, RegisterFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        login.setOnClickListener(){
            val intent = Intent(it.context, HomeActivity::class.java)

            startActivity(intent)
        }

    }


}