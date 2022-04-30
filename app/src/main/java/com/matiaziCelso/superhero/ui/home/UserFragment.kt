package com.matiaziCelso.superhero.ui.home


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.home.user_settings.HelpActivity
import com.matiaziCelso.superhero.ui.home.user_settings.PrivacyActivity
import com.matiaziCelso.superhero.ui.home.user_settings.UserSettingsActivity
import com.matiaziCelso.superhero.viewModel.UserViewModel


class UserFragment : Fragment(R.layout.fragment_user) {

    private val userViewModel: UserViewModel by viewModels()

    private lateinit var auth: FirebaseAuth
    private lateinit var userName : TextView
    private lateinit var userSettings : TextView
    private lateinit var privacy : TextView
    private lateinit var help : TextView
    private lateinit var logout : TextView
    private lateinit var userImg : ImageView
    private lateinit var userAnim : LottieAnimationView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        Log.d("FACEBOOK", "email -> ${auth.currentUser?.email}")

        userName = view.findViewById(R.id.user_name)
        userImg = view.findViewById(R.id.user_img)
        userSettings = view.findViewById(R.id.menu_1)
        help = view.findViewById(R.id.menu_3)
        privacy = view.findViewById(R.id.menu_4)
        logout = view.findViewById(R.id.menu_5)
        userAnim = view.findViewById(R.id.user_loader_anim)

        userSettings.setOnClickListener {
            val intent = Intent(context, UserSettingsActivity::class.java)
            startActivity(intent)
        }

        help.setOnClickListener {
            val intent = Intent(context, HelpActivity::class.java)
            startActivity(intent)
        }

        privacy.setOnClickListener {
            val intent = Intent(context, PrivacyActivity::class.java)
            startActivity(intent)
        }

        logout.setOnClickListener {
            auth.signOut()
            activity?.onBackPressed()
        }

        userViewModel.getUserData()
        observe()
    }

    private fun observe() {
        userViewModel.userData.observe(viewLifecycleOwner){
            if(it != null){
                userName.text = getName(it.name)
                getUserImage(it.imgUrl)
            }else{
                getUser()
            }
        }
    }

    private fun getUser() {
       userName.text = auth.currentUser!!.displayName ?: auth.currentUser!!.email ?: "Seu nome"
        if(auth.currentUser!!.photoUrl != null){
            setImage(auth.currentUser!!.photoUrl!!)
        }else{
            Log.d("USERSET", "USER PHOTO NULL")
        }
    }

    private fun getName(name: String): String {
        if(name.isEmpty().not()) return name
        return auth.currentUser!!.displayName ?: auth.currentUser!!.email ?: "Seu Nome"
    }


    private fun getUserImage(img: String){
        if(img == ""){
            setImage(auth.currentUser!!.photoUrl!!)
        }else{
            setImage(img)
        }
    }

    private fun setImage(photo: Any){
        if(photo != ""){
            Glide.with(requireContext())
                .load(photo)
                .circleCrop()
                .into(userImg)
            userAnim.isVisible = false
        }
    }

}