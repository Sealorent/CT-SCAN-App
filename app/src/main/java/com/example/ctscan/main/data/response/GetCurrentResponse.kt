package com.example.ctscan.main.data.response

import com.example.ctscan.main.data.Patient
import com.google.gson.annotations.SerializedName

data class GetCurrentResponse(
    @SerializedName("patient")
    val patient: List<Patient>
)
