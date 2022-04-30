package com.matiaziCelso.superhero.ui.home.user_settings

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import com.airbnb.lottie.LottieAnimationView
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.google.firebase.storage.FirebaseStorage
import com.matiaziCelso.superhero.R
import com.matiaziCelso.superhero.data.models.DataUser
import com.matiaziCelso.superhero.viewModel.UserViewModel
import java.io.ByteArrayOutputStream

class UserSettingsActivity : AppCompatActivity() {

    private val userViewModel: UserViewModel by viewModels()

    private lateinit var imgName: String
    private lateinit var auth: FirebaseAuth
    private lateinit var userImg : ImageView
    private lateinit var camera : CardView
    private lateinit var userEmail : TextInputEditText
    private lateinit var backBtn : ImageView
    private lateinit var name : TextInputEditText
    private lateinit var phone : TextInputEditText
    private lateinit var cpf : TextInputEditText
    private lateinit var saveBtn : Button
    private lateinit var loader: LottieAnimationView
    private var urlUserImage = ""
    private var uriImage : Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings)
        this.supportActionBar?.hide()

        auth = FirebaseAuth.getInstance()
        imgName = auth.currentUser!!.uid

        loader = findViewById(R.id.user_settings_loader)
        userEmail = findViewById(R.id.login_email_txt)
        userImg = findViewById(R.id.user_settings_img)
        camera = findViewById(R.id.user_settings_camera)
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

        camera.setOnClickListener {
            dialogPhoto(it.context)
        }

        userEmail.setText(auth.currentUser!!.email)
        userViewModel.getUserData()
        observe()
    }

    private fun observe() {

        userViewModel.loading.observe(this){
            loader.isVisible = it
        }

        userViewModel.userData.observe(this){
            if(it != null){
                name.setText(it.name)
                phone.setText(it.phone)
                cpf.setText(it.cpf)
                urlUserImage = it.imgUrl
               if(it.imgUrl.isNotEmpty()){
                   Glide.with(this)
                       .load(getUserImage(it.imgUrl))
                       .placeholder(R.drawable.img_user)
                       .into(userImg)
               }
            }
        }
    }


    private fun getUserImage(userImage: String): String? {
        if(userImage.isNotEmpty()) return userImage
        if(auth.currentUser!!.photoUrl.toString().isNotEmpty()) return auth.currentUser!!.photoUrl.toString()
        return null
    }


    private fun saveUserData(){
        val user = DataUser(
            email = "${auth.currentUser!!.email}",
            name = name.text.toString(),
            phone = phone.text.toString(),
            cpf = cpf.text.toString(),
            imgUrl = urlUserImage
        )
        userViewModel.saveUserData(user, uriImage)
    }


    private fun dialogPhoto(context: Context) {
        val items = arrayOf("Tirar foto", "Carregar foto",)
        AlertDialog
            .Builder(context)
            .setTitle("O que você deseja fazer?")
            .setItems(items) { dialog, index ->
                when (index) {
                    0 -> getPermissions()
                    1 -> getUserPhoto()
                }
                dialog.dismiss()
            }.show()
    }

    private fun getUserPhoto(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        getResultGallery.launch(intent)
    }


    private fun getPermissions(){
        val permission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
        if(permission == PackageManager.PERMISSION_GRANTED){
            val intent = Intent().apply {
                action = MediaStore.ACTION_IMAGE_CAPTURE
            }
            getResultCamera.launch(intent)
        }else{
            permissionResultCallback.launch(Manifest.permission.CAMERA)
        }
    }

    private val getResultCamera = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            val data = it.data
            data?.extras?.get("data")?.let{ image ->
                userImg.setImageBitmap(image as Bitmap)
                val uri = getImageUri(this, image)
                uriImage = uri
            }
        }
    }

    private val getResultGallery = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            val image = it.data?.data
            userImg.setImageURI(image)
            uriImage = image!!
        }
    }


    private fun getImageUri(context: Context, img: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        img.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        val path = MediaStore.Images.Media.insertImage(context.contentResolver, img, imgName, null)
        return Uri.parse(path)
    }

    private val permissionResultCallback = registerForActivityResult(
        ActivityResultContracts.RequestPermission()){
        if(it){
            Toast.makeText(this, "Premission Granted", Toast.LENGTH_SHORT).show()
            dialogPhoto(this)
        }else{
            Toast.makeText(this, "Premission Denied", Toast.LENGTH_SHORT).show()
        }
    }

}



