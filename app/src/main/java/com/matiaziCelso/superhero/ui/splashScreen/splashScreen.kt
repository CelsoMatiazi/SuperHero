package com.matiaziCelso.superhero.ui.splashScreen

import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.TesteDB
import com.matiaziCelso.superhero.ui.home.HomeActivity
import com.matiaziCelso.superhero.ui.login.LoginActivity
import com.matiaziCelso.superhero.viewModel.HomeViewModel
import java.security.MessageDigest



class splashScreen : AppCompatActivity() {

    private lateinit var animation: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        this.supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        startActivity(Intent(this,HomeActivity::class.java))

        val imagem = findViewById<ImageView>(R.id.splash_bg_animation)

        imagem.setBackgroundResource(R.drawable.bg_animation_list)
        animation = imagem.background as AnimationDrawable
        animation.start()

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 6000)
    }
}