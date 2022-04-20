package com.matiaziCelso.superhero.ui.splashScreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.ui.TesteDB
import com.matiaziCelso.superhero.ui.login.LoginActivity
import com.matiaziCelso.superhero.viewModel.HomeViewModel

class splashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        this.supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.black)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, LoginActivity::class.java))
//            startActivity(Intent(this, TesteDB::class.java))
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 4000)
    }
}