package com.example.basicbankingapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.basicbankingapp.data.UserDatabase
import dagger.hilt.android.HiltAndroidApp


/*
 This generated Hilt component is attached to the Application object’s
 lifecycle and provides dependencies to it. Additionally, it is the parent component of the app,
 which means that other components can access the dependencies that it provides.
 */
@HiltAndroidApp
class BankApplication:Application()
{
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

}