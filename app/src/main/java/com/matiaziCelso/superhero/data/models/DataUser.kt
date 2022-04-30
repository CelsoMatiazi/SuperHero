package com.matiaziCelso.superhero.data.models

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class DataUser(
    val name: String = "",
    val email: String = "",
    val phone : String = "",
    val cpf : String = "",
    val imgUrl: String = ""
)
