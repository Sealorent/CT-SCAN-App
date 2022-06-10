package com.example.ctscan.main.ui.diagnose

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.ctscan.main.data.repository.Repository
import com.example.ctscan.main.data.request.AddPatientRequest
import com.example.ctscan.main.data.request.LoginRequest
import okhttp3.MultipartBody

class DiagnoseViewModel(application: Application): AndroidViewModel(application) {
    private val repository = Repository(application)
    fun addPatient(token: String,addPatientRequest: AddPatientRequest) = repository.addPatient(token,addPatientRequest)
    fun uploadFile(token: String,file: MultipartBody.Part) = repository.uploadFile(token,file)

}