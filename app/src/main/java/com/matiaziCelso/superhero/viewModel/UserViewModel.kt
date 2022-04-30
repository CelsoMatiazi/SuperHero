package com.matiaziCelso.superhero.viewModel

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.getValue
import com.google.firebase.storage.FirebaseStorage
import com.matiaziCelso.superhero.data.models.DataUser


class UserViewModel : ViewModel() {

    private var firebaseDB = FirebaseDatabase.getInstance()
    private val firebaseStorage = FirebaseStorage.getInstance()
    private val auth = FirebaseAuth.getInstance()

    private var urlUserImage = ""
    private var uriImage : Uri? = null
    private val imgName = auth.currentUser!!.uid

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    private val _userData = MutableLiveData<DataUser?>()
    val userData: MutableLiveData<DataUser?>
        get() = _userData


    fun getUserData(){
        _loading.value = true
        val ref = firebaseDB.getReference(auth.currentUser!!.uid)
        val key = "data_user"
        ref.child(key).get().addOnSuccessListener {
            val user = it.getValue<DataUser>()
            _userData.value = user
            _loading.value = false
        }.addOnFailureListener {
            _error.value = true
        }
    }

    fun saveUserData(user: DataUser, uri : Uri? = null){
        uriImage = uri
        _loading.value = true
        if(uriImage != null){
            uploadImageToFirebase(uriImage!!, user)
        }else{
            val ref = firebaseDB.getReference(auth.currentUser!!.uid)
            val key = "data_user"
            val data = DataUser(
                email = "${auth.currentUser!!.email}",
                name = user.name,
                phone = user.phone,
                cpf = user.cpf,
                imgUrl = updateImageUrl(user.imgUrl)
            )
            ref.child(key).setValue(data).addOnSuccessListener {
                //Log.d("USERSET", "SUCESSO")
                _loading.value = false
                urlUserImage = ""
            }
            .addOnFailureListener{
                Log.d("USERSET", "ERRO")
            }

        }
    }


    private fun updateImageUrl(saveUrl: String): String{
        if(urlUserImage == "") return saveUrl
        return urlUserImage
    }

    private fun uploadImageToFirebase(img: Uri, user: DataUser) {
        val storage = firebaseStorage.getReference("user_images")
        val fileReference = storage.child("$imgName.jpg")
        img.apply {
            fileReference
                .putFile(this)
                .addOnSuccessListener {
                    val result = it.metadata?.reference?.downloadUrl
                    result?.addOnSuccessListener { url ->
                        urlUserImage = url.toString()
                        uriImage = null
                        saveUserData(user)
                    }
                }
                .addOnFailureListener {  }
                .removeOnProgressListener {  }
        }

    }

}