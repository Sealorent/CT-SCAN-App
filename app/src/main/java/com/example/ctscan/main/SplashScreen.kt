package com.example.ctscan.main

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import com.example.ctscan.databinding.ActivitySplashScreenBinding
import com.example.ctscan.main.Utils.Const.TIME_SPLASH
import com.example.ctscan.main.Utils.SessionManager
import com.example.ctscan.main.ui.login.LoginActivity
import com.example.ctscan.main.ui.menu.MenuActivity

class SplashScreen : AppCompatActivity() {
    private lateinit var splashBinding : ActivitySplashScreenBinding
    private lateinit var pref : SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        val handler = Handler(mainLooper)
        pref = SessionManager(this)
        val isLogin = pref.isLogin
        handler.postDelayed({
            when {
                isLogin -> {
                    val intent = Intent(this@SplashScreen, MenuActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else -> {
                    val intent = Intent(this@SplashScreen, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        },TIME_SPLASH)
    }
}