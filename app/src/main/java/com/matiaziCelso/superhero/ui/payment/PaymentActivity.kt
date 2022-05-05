package com.matiaziCelso.superhero.ui.payment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.matiaziCelso.superhero.R

class PaymentActivity : AppCompatActivity() {

    private lateinit var card: View
    private lateinit var pix: View
    private lateinit var backBtn : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)
        this.supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.lightGreen)

        card = findViewById(R.id.payment_card_1)
        pix = findViewById(R.id.payment_card_2)
        backBtn = findViewById(R.id.payment_back_btn)

        backBtn.setOnClickListener {
            finish()
        }

        card.setOnClickListener {
            val intent = Intent(this, CardPayActivity::class.java)
            startActivity(intent)
        }

        pix.setOnClickListener {
            val intent = Intent(this, PixPayActivity::class.java)
            startActivity(intent)
        }

    }
}