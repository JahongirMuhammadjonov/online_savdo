package com.example.onlinesavdo.screen.view

import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.onlinesavdo.db.AppDatabese
import com.orhanobut.hawk.Hawk

class   App: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        Hawk.init(this).build()
        AppDatabese.initDatabase(this)
    }
}