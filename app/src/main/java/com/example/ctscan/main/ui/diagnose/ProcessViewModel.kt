package com.example.ctscan.main.ui.diagnose

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.ctscan.main.data.repository.Repository
import com.example.ctscan.main.data.request.AddPatientRequest
import okhttp3.MultipartBody

class ProcessViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = Repository(application)
    fun getCurrent(token: String) = repository.getRecentUser(token)
}