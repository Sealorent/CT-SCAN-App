package com.example.ctscan.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.ctscan.databinding.ActivitySplashScreenBinding
import com.example.ctscan.main.Utils.Const.TIME_SPLASH
import com.example.ctscan.main.ui.login.LoginActivity

class SplashScreen : AppCompatActivity() {
    private lateinit var splashBinding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        val handler = Handler(mainLooper)
        handler.postDelayed({
            LoginActivity.start(this)
            finish()
        },TIME_SPLASH)
    }
}