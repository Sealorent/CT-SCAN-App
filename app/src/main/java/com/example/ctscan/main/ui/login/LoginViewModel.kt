package com.example.ctscan.main.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.ctscan.main.data.repository.Repository
import com.example.ctscan.main.data.request.LoginRequest

class LoginViewModel(application: Application) : AndroidViewModel(application){
    private val repository = Repository(application)
    fun signIn(loginRequest : LoginRequest) = repository.login(loginRequest)
}