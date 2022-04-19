package com.matiaziCelso.superhero.ui.login

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.login.LoginManager
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.tasks.Task
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FacebookAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.home.HomeActivity
import com.matiaziCelso.superhero.utils.FacebookCustomCallback
import com.matiaziCelso.superhero.utils.GoogleLogInActivityContract


class LoginFragment : Fragment(R.layout.fragment_login) {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var loader: LottieAnimationView

    private val callbackManager = CallbackManager.Factory.create()
    private val loginManager = LoginManager.getInstance()

    private val googleSignInRequest = registerForActivityResult(
        GoogleLogInActivityContract(),
        ::onGoogleSignInResult
    )

    private val googleSignInOptions : GoogleSignInOptions
        get() = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .requestProfile()
            .build()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth = FirebaseAuth.getInstance()

        val googleBtn: MaterialButton = view.findViewById(R.id.login_google)
        val facebookBtn: MaterialButton = view.findViewById(R.id.login_facebook)

        val register : TextView = view.findViewById(R.id.txt_cadastrar)
        val login : TextView = view.findViewById(R.id.login_entrar_btn)
        email = view.findViewById(R.id.login_email_txt)
        password = view.findViewById(R.id.login_pass_txt)

        loader = view.findViewById(R.id.login_loader)

        register.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.login_frag_container, RegisterFragment())
                ?.addToBackStack(null)
                ?.commit()
        }

        login.setOnClickListener(){
            loginWithEmailAndPassword()
        }


        googleBtn.setOnClickListener {
            googleSignInRequest.launch(googleSignInOptions)
        }

        facebookBtn.setOnClickListener {
            loginWithFacebook()
        }

        registerFacebookCallback()
    }


    private fun loginWithEmailAndPassword(){
        loader.isVisible = true
        auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener {
                loader.isVisible = false
                if(it.isSuccessful){
                    sendToHome()
                }else{
                    showDialog("Verifique seu email e senha e tente novamente.")
                    //Toast.makeText(requireContext(), "Falha no Login", Toast.LENGTH_LONG).show()
                }
            }
    }


    private fun onGoogleSignInResult(result: GoogleLogInActivityContract.Result?) {
        loader.isVisible = true
        if(result is GoogleLogInActivityContract.Result.Success){
            val token = result.googleSignInAccount.idToken
            Log.d(TAG, "TOKEN GOOGLE => $token")
            token?.let {
                loginWithGoogle(token)
            }
        }else{
            loader.isVisible = false
            showDialog("Tente novamente mais tarde.")
        }
    }


    private fun loginWithGoogle(token: String){
        val credential = GoogleAuthProvider.getCredential(token, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                    Result: Task<AuthResult> ->
                if(Result.isSuccessful){
                    sendToHome()
                }
                loader.isVisible = false
            }
            .addOnFailureListener {
                showDialog(it.message.toString())
            }
    }



    private fun loginWithFacebook(){
        loader.isVisible = true
        loginManager.logInWithReadPermissions(
            this,
            callbackManager,
            listOf("public_profile", "email"))
    }

    private fun registerFacebookCallback() {
        loginManager.registerCallback(callbackManager, FacebookCustomCallback {
            if(it is FacebookCustomCallback.Result.Success){
                handleFacebookAccessToken(it.token)
            }
            if(it is FacebookCustomCallback.Result.Error){
                showDialog(it.exception.message.toString())
                loader.isVisible = false
            }
        })
    }


    private fun handleFacebookAccessToken(token: AccessToken) {
        Log.d(TAG, "handleFacebookAccessToken:$token")
        val credential = FacebookAuthProvider.getCredential(token.token)
        auth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    loader.isVisible = false
                    sendToHome()
                }
            }
            .addOnFailureListener {
                loader.isVisible = false
                showDialog(it.message.toString())
            }

    }

    private fun sendToHome(){
        val intent = Intent(context, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun showDialog(message: String){
        val alertDialog = AlertDialog.Builder(context, R.style.AppCompatAlertDialogStyle)
        alertDialog
            .setTitle("Super Hero")
            .setMessage("Desculpe, não conseguimos efetuar o login.\n$message")
            .setCancelable(false)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    companion object{
        const val TAG = "SOCIAL_LOGIN"
    }

}