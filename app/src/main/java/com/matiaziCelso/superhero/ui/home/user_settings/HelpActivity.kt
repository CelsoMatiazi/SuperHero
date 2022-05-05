package com.matiaziCelso.superhero.ui.home.user_settings

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.matiaziCelso.superhero.R

class HelpActivity : AppCompatActivity() {

    private lateinit var backBtn : ImageView
    private lateinit var panicCounter : TextView
    private lateinit var panicButton : LottieAnimationView
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        this.supportActionBar?.hide()

        backBtn = findViewById(R.id.help_back_btn)
        panicButton = findViewById(R.id.help_anim)
        panicCounter = findViewById(R.id.panic_counter)

        backBtn.setOnClickListener {
            finish()
        }

        panicButton.setOnClickListener {
            panicButton()
        }
    }


    private fun panicButton(){
        counter ++
        if(counter > 4){
            panicCounter.isVisible = true
            panicCounter.text = counter.toString()
        }

        if(counter == 10){
            counter = 0
            panicCounter.isVisible = false
            val intent = Intent(this, SecretActivity::class.java)
            startActivity(intent)
        }
    }
}