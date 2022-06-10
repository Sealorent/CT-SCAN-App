package com.example.ctscan.main.data.request

import com.google.gson.annotations.SerializedName

data class AddPatientRequest(
    @SerializedName("name")
    val name: String?,

    @SerializedName("nik")
    val nik: String?,

    @SerializedName("age")
    val age: String?,

    @SerializedName("gender")
    val gender: String?,

    @SerializedName("address")
    val address: String

)
