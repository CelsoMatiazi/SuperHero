package com.matiaziCelso.superhero.ui.home.user_settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.matiaziCelso.superhero.R

class HelpActivity : AppCompatActivity() {

    private lateinit var backBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help)
        this.supportActionBar?.hide()

        backBtn = findViewById(R.id.help_back_btn)

        backBtn.setOnClickListener {
            finish()
        }
    }
}