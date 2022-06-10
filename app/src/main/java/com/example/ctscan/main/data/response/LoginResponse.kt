package com.example.ctscan.main.data.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    var token: String
)
