package com.matiaziCelso.superhero.ui.payment

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.airbnb.lottie.LottieAnimationView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.CartItems
import com.matiaziCelso.superhero.data.models.BoughtItem
import com.matiaziCelso.superhero.ui.home.HomeActivity
import com.matiaziCelso.superhero.ui.login.LoginActivity
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PixPayActivity : AppCompatActivity() {

    private lateinit var pixCode: TextInputEditText
    private lateinit var inputText: TextInputLayout
    private lateinit var backBtn : ImageView
    private lateinit var button : Button
    private lateinit var clipBoardManager : ClipboardManager
    private lateinit var animation: LottieAnimationView

    private var firebaseDB = FirebaseDatabase.getInstance()
    private val auth = FirebaseAuth.getInstance()

    @RequiresApi(Build.VERSION_CODES.O)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pix_pay)
        supportActionBar?.hide()
        window.statusBarColor = ContextCompat.getColor(this, R.color.lightGreen)

        pixCode = findViewById(R.id.pix_pay_txt)
        inputText = findViewById(R.id.pix_pay_input)
        backBtn = findViewById(R.id.pix_pay_back_btn)
        button = findViewById(R.id.pix_pay_button)
        animation = findViewById(R.id.pix_pay_clock_anim)

        backBtn.setOnClickListener {
            finish()
        }

        pixCode.setText("s8jcjfh48fbvAyjdnckdakr994kfcdoRe9284dchh34sj35kird")

        pixCode.setOnClickListener {
            copyText()
        }

        button.setOnClickListener {
            saveItemsOnFirebase()
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveItemsOnFirebase(){
        animation.isVisible = true
        val ref = firebaseDB.getReference(auth.currentUser!!.uid)
        val key = "Purchases"
        val currentTime = currentTime().replace(".", "w")

        val convertItem = CartItems.items.map {
            BoughtItem(
                it.title,
                LocalDateTime.now().dayOfMonth.toString(),
                LocalDateTime.now().month.toString(),
                currentTime(),
                0,
                it.value
            )
        }

        ref.child(key).child(currentTime).setValue(convertItem)
            .addOnSuccessListener {
                showDialog()
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


    private fun copyText(){
        val text = pixCode.text.toString()
        if(text.isNotEmpty()){
            clipBoardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clipData = ClipData.newPlainText("Key", text)
            clipBoardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "Código copiado", Toast.LENGTH_LONG).show()
        }
    }


    private fun showDialog(){
        animation.isVisible = false
        val alertDialog = AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle)
        alertDialog
            .setTitle("Super Hero")
            .setMessage("Seu pedido foi concluido com sucesso e sera liberado assim que o pagamento for confirmado.")
            .setCancelable(false)
            .setPositiveButton("OK") { _, _ ->
                clearCart()
            }.show()
    }


   private fun clearCart(){
       val ref = firebaseDB.getReference(auth.currentUser!!.uid)
       val key = "cart"

       ref.child(key).removeValue().addOnSuccessListener {
           CartItems.items.clear()
           val intent = Intent(this, HomeActivity::class.java)
           intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
           startActivity(intent)
       }
       .addOnFailureListener{
           Log.d("CART", "ERRO")
       }
   }

}