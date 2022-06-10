package com.example.ctscan.main.Utils

import android.view.View
import com.example.ctscan.main.data.response.AddPatientResponse
import com.example.ctscan.main.data.response.GetCurrentResponse
import com.example.ctscan.main.data.response.LoginResponse

interface ViewStateCallback <T>{
    fun onSuccess(data: T)
    fun onLoading()
    fun onFailed(message: String?)

    val invisible: Int
        get() = View.INVISIBLE

    val visible: Int
        get() = View.VISIBLE
}