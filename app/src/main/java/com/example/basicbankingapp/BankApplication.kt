package com.example.basicbankingapp

import android.app.Application
import com.example.basicbankingapp.data.UserDatabase

class BankApplication:Application()
{

    private val database by lazy { UserDatabase.getInstance(applicationContext)}
    val dao by lazy { database.userDao() }

}