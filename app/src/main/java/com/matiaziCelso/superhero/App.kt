package com.matiaziCelso.superhero

import android.app.Application
import android.content.Context
import com.matiaziCelso.superhero.data.db.DataBaseFactory

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        DataBaseFactory.build(applicationContext)
        appContext = applicationContext
    }

    companion object{
        var appContext: Context? = null
    }
}