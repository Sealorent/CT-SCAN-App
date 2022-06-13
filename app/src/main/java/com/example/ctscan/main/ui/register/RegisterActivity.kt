package com.example.ctscan.main.ui.register

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
import com.example.ctscan.databinding.ActivityRegisterBinding
import com.example.ctscan.main.Utils.Const
import com.example.ctscan.main.Utils.ViewStateCallback
import com.example.ctscan.main.data.Resource
import com.example.ctscan.main.data.request.RegisterRequest
import com.example.ctscan.main.data.response.AddPatientResponse
import com.example.ctscan.main.data.response.RegisterResponse
import com.example.ctscan.main.ui.login.LoginActivity

class RegisterActivity : AppCompatActivity() , ViewStateCallback<RegisterResponse>{
    private lateinit var registerBinding : ActivityRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)
        register()
        goLogin()
    }

    private fun goLogin(){
        registerBinding.toLogin.setOnClickListener {
            val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun register(){
        registerBinding.btnRegister.setOnClickListener {
            val email = registerBinding.edtEmail.text.toString()
            val name = registerBinding.edtName.text.toString()
            val password = registerBinding.edtPassword.text.toString()
            closeKeyboard()
            Handler(Looper.getMainLooper()).postDelayed({
                when {
                    email.isBlank() -> registerBinding.edtEmail.error = getString(R.string.error_empty_email)
                    name.isBlank() -> registerBinding.edtName.error = getString(R.string.error_empty_name)
                    password.isBlank() -> registerBinding.edtPassword.error = getString(R.string.error_empty_password)
                    else -> {
                        val registerRequest = RegisterRequest( email = email, name = name, password = password, )
                        viewModel.register(registerRequest).observe(this) {
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


    override fun onSuccess(data: RegisterResponse) {
        registerBinding.apply {
            val data = data
            try {
                Toast.makeText(this@RegisterActivity, data.message, Toast.LENGTH_SHORT).show()
            }finally {
                val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }


        }
    }

    override fun onLoading() {
        registerBinding.apply {
            Toast.makeText(this@RegisterActivity, "Loading...", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFailed(message: String?) {
        val message = message
        registerBinding.apply {
            Toast.makeText(this@RegisterActivity, message.toString(), Toast.LENGTH_SHORT).show()
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