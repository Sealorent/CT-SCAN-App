package com.example.ctscan.main.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import com.example.ctscan.R
import com.example.ctscan.databinding.ActivityLoginBinding
import com.example.ctscan.main.Utils.Const
import com.example.ctscan.main.Utils.ViewStateCallback
import com.example.ctscan.main.data.Resource
import com.example.ctscan.main.data.request.LoginRequest
import com.example.ctscan.main.data.response.AddPatientResponse
import com.example.ctscan.main.data.response.LoginResponse
import com.example.ctscan.main.ui.menu.MenuActivity
import com.example.ctscan.main.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity(), ViewStateCallback<LoginResponse> {
    private lateinit var loginBinding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)
        signUp()
        logIn()
    }

    private fun logIn(){
        loginBinding.btnLogin.setOnClickListener {
            val email = loginBinding.edtEmail.text.toString()
            val password = loginBinding.edtPassword.text.toString()
            closeKeyboard()
            Handler(Looper.getMainLooper()).postDelayed({
                when {
                    email.isBlank() -> loginBinding.edtEmail.error = getString(R.string.error_empty_email)
                    password.isBlank() -> loginBinding.edtPassword.error = getString(R.string.error_empty_password)
                    else -> {
                        val loginRequest = LoginRequest( email = email, password = password)
                        viewModel.signIn(loginRequest).observe(this) {
                            when (it) {
                                is Resource.Error -> onFailed(it.message.toString())
                                is Resource.Loading -> onLoading()
                                is Resource.Success -> it.data?.let { it1 -> onSuccess(it1) }
                            }
                        }
                    }
                }
            }, Const.ACTION_DELAYED_TIME)

        }
    }

    override fun onSuccess(data: LoginResponse) {
        loginBinding.apply {
            try {
                Toast.makeText(this@LoginActivity, "success login", Toast.LENGTH_SHORT).show()
            }finally {
                val intent = Intent(this@LoginActivity, MenuActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }

    override fun onLoading() {
        loginBinding.apply {
            Toast.makeText(this@LoginActivity, "Loading...", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFailed(message: String?) {
        val message = message
        loginBinding.apply {
            Toast.makeText(this@LoginActivity, message.toString(), Toast.LENGTH_SHORT).show()
        }
    }


    private fun signUp (){
        loginBinding.toRegister.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun closeKeyboard() {
        val view: View? = this.currentFocus
        if (view != null) {
            val manager: InputMethodManager = getSystemService(
                Context.INPUT_METHOD_SERVICE
            ) as InputMethodManager
            manager
                .hideSoftInputFromWindow(
                    view.windowToken, 0
                )
        }
    }



}