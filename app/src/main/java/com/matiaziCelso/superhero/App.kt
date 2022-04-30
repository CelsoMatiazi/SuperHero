package com.matiaziCelso.superhero

import android.app.Application
import android.content.Context
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.setAutoLogAppEventsEnabled
import com.facebook.appevents.AppEventsLogger
import com.google.firebase.ktx.Firebase
import com.google.firebase.messaging.FirebaseMessagingRegistrar
import com.google.firebase.messaging.ktx.messaging
import com.matiaziCelso.superhero.data.db.DataBaseFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        DataBaseFactory.build(this)
        appContext = applicationContext
        AppEventsLogger.activateApp(this)
        Firebase.messaging.subscribeToTopic("all")
    }

    companion object{
        var appContext: Context? = null
    }
}