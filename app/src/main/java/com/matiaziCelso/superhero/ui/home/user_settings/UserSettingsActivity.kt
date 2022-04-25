package com.matiaziCelso.superhero.ui.home.user_settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.ktx.getValue
import com.matiaziCelso.superhero.R

class UserSettingsActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var userImg : ImageView
    private lateinit var userEmail : TextInputEditText
    private lateinit var backBtn : ImageView
    private lateinit var name : TextInputEditText
    private lateinit var phone : TextInputEditText
    private lateinit var cpf : TextInputEditText
    private lateinit var saveBtn : Button
    private lateinit var loader: LottieAnimationView
    private val firebaseDB = FirebaseDatabase.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)
        this.supportActionBar?.hide()


        auth = FirebaseAuth.getInstance()

        loader = findViewById(R.id.user_settings_loader)
        userEmail = findViewById(R.id.login_email_txt)
        userImg = findViewById(R.id.user_settings_img)
        backBtn = findViewById(R.id.user_settings_back_btn)
        saveBtn = findViewById(R.id.user_settings_save_btn)
        name = findViewById(R.id.user_settings_name_txt)
        phone = findViewById(R.id.userSettings_phone_txt)
        cpf = findViewById(R.id.user_settings_cpf_txt)

        backBtn.setOnClickListener {
            finish()
        }

        saveBtn.setOnClickListener {
            saveUserData()
        }

        getUser()
    }

    private fun getUser() {

        val ref = firebaseDB.getReference(auth.currentUser!!.uid)
        val key = "data_user"
        userEmail.setText(auth.currentUser!!.email)
        ref.child(key).get().addOnSuccessListener {
            val user = it.getValue<DataUser>()
            if (user != null) {
                name.setText(user.name)
                phone.setText(user.phone)
                cpf.setText(user.cpf)
            }
            loader.isVisible = false
        }.addOnFailureListener {
            println("DEU RUIMMMMMM")
        }

        Glide.with(this)
            .load(auth.currentUser!!.photoUrl)
            .placeholder(R.drawable.img_user)
            .into(userImg)

    }


    private fun saveUserData(){
        loader.isVisible = true
        val ref = firebaseDB.getReference(auth.currentUser!!.uid)
        val key = "data_user"
        val user = DataUser(
            email = "${auth.currentUser!!.email}",
            name = name.text.toString(),
            phone = phone.text.toString(),
            cpf = cpf.text.toString()
        )
        ref.child(key).setValue(user).addOnSuccessListener {
            loader.isVisible = false
        }
    }
}

@IgnoreExtraProperties
data class DataUser(
    val name: String = "",
    val email: String = "",
    val phone : String = "",
    val cpf : String = "",
)