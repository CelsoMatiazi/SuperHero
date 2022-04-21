package com.matiaziCelso.superhero

import android.app.Application
import android.content.Context
import com.facebook.FacebookSdk
import com.facebook.FacebookSdk.setAutoLogAppEventsEnabled
import com.facebook.appevents.AppEventsLogger
import com.matiaziCelso.superhero.data.db.DataBaseFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        DataBaseFactory.build(this)
        appContext = applicationContext
        AppEventsLogger.activateApp(this)

    }

    companion object{
        var appContext: Context? = null
    }
}