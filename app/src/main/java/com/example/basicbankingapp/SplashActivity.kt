package com.example.basicbankingapp

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.WindowManager

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
     // Remember that you should never show the action bar if the
    // status bar is hidden, so hide that too if necessary.
        actionBar?.hide()
        setContentView(R.layout.activity_splash)


      Handler().postDelayed({
        val intent = Intent(this@SplashActivity,MainActivity::class.java)
        startActivity(intent)
          finish()
      },1000)
    }


}