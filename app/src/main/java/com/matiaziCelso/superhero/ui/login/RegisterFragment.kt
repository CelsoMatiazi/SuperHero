package com.matiaziCelso.superhero.ui.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.home.HomeActivity


class RegisterFragment : Fragment(R.layout.fragment_register) {


    private lateinit var auth: FirebaseAuth
    private lateinit var email: TextInputEditText
    private lateinit var pass1: TextInputEditText
    private lateinit var pass2: TextInputEditText
    private lateinit var loader: LottieAnimationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        val returnLogin: TextView = view.findViewById(R.id.return_login)
        val registerBtn: Button = view.findViewById(R.id.cadastrar_btn)
        email = view.findViewById(R.id.user_email_txt)
        pass1 = view.findViewById(R.id.user_pass1)
        pass2 = view.findViewById(R.id.user_pass2)
        loader = view.findViewById(R.id.register_loader)

        returnLogin.setOnClickListener {
           activity?.onBackPressed()
        }

        registerBtn.setOnClickListener {
            validate()
        }

    }


    private fun validate(){
        if(email.text!!.contains("@").not() || email.text!!.contains(".").not()){
            Toast.makeText(requireContext(), "Email invalido", Toast.LENGTH_LONG).show()
        }else{
            if(pass1.text?.isEmpty()!!){
                Toast.makeText(requireContext(), "Senha invalida", Toast.LENGTH_LONG).show()
            }else{
                if(pass1.text?.length!! < 6){
                    Toast.makeText(requireContext(), "Senha muito curta", Toast.LENGTH_LONG).show()
                }else{
                    if(pass1.text.toString() != pass2.text.toString()){
                        Toast.makeText(requireContext(), "Senhas não conferem", Toast.LENGTH_LONG).show()
                    }else{
                        createNewAccount()
                    }
                }
            }
        }
    }

    private fun createNewAccount(){
        loader.isVisible = true
        auth.createUserWithEmailAndPassword(email.text.toString(), pass1.text.toString())
            .addOnCompleteListener {
                if(it.isSuccessful){
                    sendToHome()
                }else{
                    showDialog(it.exception?.message.toString())
                }
                loader.isVisible = false
            }
    }


    private fun showDialog(message: String){
        val alertDialog = AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
        alertDialog
            .setTitle("Super Hero")
            .setMessage("Desculpe, não conseguimos efetuar seu cadastro.\n\n\"$message\"")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }


    private fun sendToHome(){
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
    }


}