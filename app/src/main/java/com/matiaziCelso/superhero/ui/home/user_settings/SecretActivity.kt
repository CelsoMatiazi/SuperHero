package com.matiaziCelso.superhero.ui.home.user_settings

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.Resource
import com.matiaziCelso.superhero.R

class SecretActivity : AppCompatActivity() {

    private lateinit var img1 : ImageView
    private lateinit var img2 : ImageView
    private lateinit var img3 : ImageView
    private lateinit var img4 : ImageView
    private lateinit var backBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret)

        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.amber)

        img1 = findViewById(R.id.panic_img_1)
        img2 = findViewById(R.id.panic_img_2)
        img3 = findViewById(R.id.panic_img_3)
        img4 = findViewById(R.id.panic_img_4)
        backBtn = findViewById(R.id.secret_back_btn)

        val circle1 = findViewById<ImageView>(R.id.panic_circle_1)
        val circle2 = findViewById<ImageView>(R.id.panic_circle_2)
        val circle3 = findViewById<ImageView>(R.id.panic_circle_3)
        val circle4 = findViewById<ImageView>(R.id.panic_circle_4)

        Glide.with(this)
            .load(R.drawable.denis)
            .circleCrop()
            .into(img1)


        Glide.with(this)
            .load(R.drawable.jonatas)
            .circleCrop()
            .into(img2)

        Glide.with(this)
            .load(R.drawable.celso)
            .circleCrop()
            .into(img3)

        Glide.with(this)
            .load(R.drawable.andre)
            .circleCrop()
            .into(img4)

        animation(circle1)
        animation(circle2)
        animation(circle3)
        animation(circle4)


        backBtn.setOnClickListener {
            finish()
        }
    }

    private fun animation(circle: ImageView){
        circle.animate()
            .scaleX(1.1f)
            .scaleY(1.1f)
            .alpha(1f)
            .setDuration(2000)
            .withEndAction {
                circle.scaleX = .9f
                circle.scaleY = .9f
                circle.alpha = .5f
                animation(circle)
            }
    }

}