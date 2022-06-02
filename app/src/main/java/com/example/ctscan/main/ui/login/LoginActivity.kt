package com.example.ctscan.main.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ctscan.databinding.ActivityLoginBinding
import com.example.ctscan.main.menu.MenuActivity
import com.example.ctscan.main.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        signUp()
        logIn()
    }

    private fun logIn(){
        loginBinding.btnLogin.setOnClickListener{
            MenuActivity.start(this)
            finish()
        }
    }


    private fun signUp (){
        loginBinding.toRegister.setOnClickListener {
            RegisterActivity.start(this)
            finish()
        }
    }
    companion object {

        fun start(context: Context) {
            val intent = Intent(context, LoginActivity::class.java)
            context.startActivity(intent)
        }
    }
}