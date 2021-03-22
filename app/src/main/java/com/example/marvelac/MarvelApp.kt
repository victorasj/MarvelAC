package com.example.marvelac

import android.app.Application
import androidx.room.Room
import com.example.marvelac.data.database.MarvelDatabase
import com.facebook.stetho.Stetho

class MarvelApp: Application() {

    override fun onCreate() {
        initServiceLocator()
        Stetho.initializeWithDefaults(this);
        super.onCreate()
    }
}