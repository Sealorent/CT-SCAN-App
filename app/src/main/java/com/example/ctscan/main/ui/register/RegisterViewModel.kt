package com.example.ctscan.main.ui.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.ctscan.main.data.repository.Repository
import com.example.ctscan.main.data.request.LoginRequest
import com.example.ctscan.main.data.request.RegisterRequest

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)
    fun register(registerRequest : RegisterRequest) = repository.register(registerRequest)
}