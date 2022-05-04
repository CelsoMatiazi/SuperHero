package com.matiaziCelso.superhero.data.models

import com.google.firebase.database.IgnoreExtraProperties


@IgnoreExtraProperties
data class BoughtItem(

    val title: String = "",
    val day: String = "",
    val month: String = "",
    val date: String = "",
    val status: Int = 0,
    val price: Double = 0.0,

)

