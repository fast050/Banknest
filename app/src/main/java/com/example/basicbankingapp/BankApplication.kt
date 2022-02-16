package com.example.basicbankingapp

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.basicbankingapp.data.UserDatabase

class BankApplication:Application()
{

    private val database by lazy { UserDatabase.getInstance(applicationContext)}
    val dao by lazy { database.userDao() }

    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

    }

}