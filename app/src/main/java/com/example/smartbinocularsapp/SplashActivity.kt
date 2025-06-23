package com.example.smartbinocularsapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(Looper.getMainLooper()).postDelayed({
            // Replace SignInActivity::class.java with your actual sign-in activity class
            startActivity(Intent(this, SignInActivity::class.java))
            finish()
        }, 1000)
    }
}