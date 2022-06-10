package com.example.ctscan.main.data.response

import com.example.ctscan.main.data.Patient
import com.google.gson.annotations.SerializedName

data class ResponseGetAllPatient(
    @SerializedName("patients")
    val listPatient: List<Patient>
)
