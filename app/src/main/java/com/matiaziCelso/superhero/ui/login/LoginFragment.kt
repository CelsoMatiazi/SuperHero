package com.matiaziCelso.superhero.ui.login

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.FavItems
import com.matiaziCelso.superhero.data.models.ComicItem


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = FirebaseAuth.getInstance()

        val register : TextView = view.findViewById(R.id.txt_cadastrar)
        val login : TextView = view.findViewById(R.id.login_entrar_btn)
        email = view.findViewById(R.id.login_email_txt)
        password = view.findViewById(R.id.login_pass_txt)

        register.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.login_frag_container, RegisterFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        login.setOnClickListener(){
            loginWithEmailAndPassword()


//            val intent = Intent(it.context, HomeActivity::class.java)
//            startActivity(intent)
        }

    }


    private fun loginWithEmailAndPassword(){
        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener {
                if(it.isSuccessful){
                    Toast.makeText(requireContext(), "Login success", Toast.LENGTH_LONG).show()
                }else{
                    showDialog()
                    //Toast.makeText(requireContext(), "Falha no Login", Toast.LENGTH_LONG).show()
                }
            }
    }


    private fun showDialog(){
        val alertDialog = AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
        alertDialog
            .setTitle("Super Hero")
            .setMessage("Desculpe, não conseguimos efetuar o login, verifique seu email e senha e tente novamente.")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }


}