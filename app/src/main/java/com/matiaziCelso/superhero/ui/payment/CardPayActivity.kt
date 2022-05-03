package com.matiaziCelso.superhero.ui.payment

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.CartItems
import com.matiaziCelso.superhero.data.models.BoughtItem
import com.matiaziCelso.superhero.ui.home.HomeActivity
import com.santalu.maskara.widget.MaskEditText
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

class CardPayActivity : AppCompatActivity() {

    private lateinit var backBtn : ImageView
    private lateinit var cardName : TextInputEditText
    private lateinit var cardNumber : MaskEditText
    private lateinit var cardExp : MaskEditText
    private lateinit var cardCVV : TextInputEditText
    private lateinit var button : Button
    private lateinit var animation : LottieAnimationView

    private var firebaseDB = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_pay)
        supportActionBar?.hide()

        backBtn = findViewById(R.id.card_pay_back_btn)
        cardName = findViewById(R.id.card_pay_name_txt)
        cardNumber = findViewById(R.id.card_pay_number_txt)
        cardExp = findViewById(R.id.card_pay_expiry_txt)
        cardCVV = findViewById(R.id.card_pay_cvv_txt)
        button = findViewById(R.id.card_pay_buttom)
        animation = findViewById(R.id.card_pay_clock_anim)

        backBtn.setOnClickListener {
            finish()
        }

        button.setOnClickListener {
            validateForm()
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun validateForm(){
        if(cardNumber.text?.length ?: 0 < 19){
            Toast.makeText(this, "Numero de cartão invalido.", Toast.LENGTH_LONG).show()
        }else{
            if(cardName.text?.length ?: 0 < 5){
                Toast.makeText(this, "Nome muito curto", Toast.LENGTH_LONG).show()
            }else{
                if(cardExp.text?.length ?: 0  < 5){
                    Toast.makeText(this, "Data invalida.", Toast.LENGTH_LONG).show()
                }else{
                    if(cardCVV.text?.length ?: 0 < 3){
                        Toast.makeText(this, "CVV invalido", Toast.LENGTH_LONG).show()
                    }else{
                        animation.isVisible = true
                        saveItemsOnFirebase()
                    }
                }
            }
        }
    }


    private fun showDialog(){
        animation.isVisible = false
        val alertDialog = AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
        alertDialog
            .setTitle("Super Hero")
            .setMessage("Seu pedido foi concluido com sucesso e sera liberado assim que o pagamento for processado.")
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                CartItems.items.clear()
                val intent = Intent(this, HomeActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }.show()
    }

    private fun clearCartFirebase(){
        val ref = firebaseDB.getReference(auth.currentUser!!.uid)
        val key = "cart"

        ref.child(key).removeValue().addOnSuccessListener {
            showDialog()
        }
            .addOnFailureListener{
                Log.d("CART", "ERRO")
            }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveItemsOnFirebase(){
        val ref = firebaseDB.getReference(auth.currentUser!!.uid)
        val key = "Purchases"
        val currentTime = currentTime().replace(".", "w")
        val status = paymentStatusRandom()

        val convertItem = CartItems.items.map {
            BoughtItem(
                it.title,
                LocalDateTime.now().dayOfMonth.toString(),
                LocalDateTime.now().month.toString(),
                currentTime(),
                status,
                it.value
            )
        }

        ref.child(key).child(currentTime).setValue(convertItem)
            .addOnSuccessListener {
                clearCartFirebase()
            }
            .addOnFailureListener{
                Log.d("CART", "ERRO")
            }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun currentTime(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSS")
        return current.format(formatter)
    }

    private fun paymentStatusRandom(): Int {
        return Random.nextInt(-1, 1)
    }
}