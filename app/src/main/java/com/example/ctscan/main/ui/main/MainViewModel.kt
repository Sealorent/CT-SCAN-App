package com.example.ctscan.main.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.ctscan.main.data.repository.Repository
import com.example.ctscan.main.data.request.LoginRequest

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)
    fun getAllPatient(token : String) = repository.getAllPatient(token)
}