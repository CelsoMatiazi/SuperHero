package com.matiaziCelso.superhero.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.matiaziCelso.superhero.R


class UserFragment : Fragment(R.layout.fragment_user) {

    private lateinit var auth: FirebaseAuth
    private lateinit var userName : TextView
    private lateinit var userImg : ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()

        userName = view.findViewById(R.id.user_name)
        userImg = view.findViewById(R.id.user_img)

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