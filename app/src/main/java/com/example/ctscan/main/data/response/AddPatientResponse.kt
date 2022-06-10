package com.example.ctscan.main.data.response

import com.google.gson.annotations.SerializedName

data class AddPatientResponse(
    @SerializedName("message")
    var message: String?
)
