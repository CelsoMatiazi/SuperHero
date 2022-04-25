package com.matiaziCelso.superhero.ui.home


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.home.user_settings.HelpActivity
import com.matiaziCelso.superhero.ui.home.user_settings.PrivacyActivity
import com.matiaziCelso.superhero.ui.home.user_settings.UserSettingsActivity


class UserFragment : Fragment(R.layout.fragment_user) {

    private lateinit var auth: FirebaseAuth
    private lateinit var userName : TextView
    private lateinit var userSettings : TextView
    private lateinit var privacy : TextView
    private lateinit var help : TextView
    private lateinit var userImg : ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        userName = view.findViewById(R.id.user_name)
        userImg = view.findViewById(R.id.user_img)
        userSettings = view.findViewById(R.id.menu_1)
        help = view.findViewById(R.id.menu_3)
        privacy = view.findViewById(R.id.menu_4)

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

        getUser()
    }

    private fun getUser() {
           userName.text = auth.currentUser!!.displayName ?: auth.currentUser!!.email ?: "Name"
           Glide.with(requireContext())
               .load(auth.currentUser!!.photoUrl)
               .circleCrop()
               .placeholder(R.drawable.img_user)
               .into(userImg)

    }

}