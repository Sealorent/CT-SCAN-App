package com.example.ctscan.main.data

import android.graphics.drawable.Drawable
import android.media.Image
import android.media.ImageReader
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*
@Parcelize
data class Patient(
    @SerializedName("id")
    var id: Long ? = null,
    @SerializedName("name")
    var name: String ? = null,
    @SerializedName("nik")
    var nik : String ? = null,
    @SerializedName("age")
    var age : String     ? = null,
    @SerializedName("gender")
    var gender: String ? = null,
    @SerializedName("address")
    var address: String ? = null,
    @SerializedName("ct_image")
    var ct_image: String ? = null,
    @SerializedName("covid_status")
    var covid_status: String? = null,
    @SerializedName("severity")
    var severity : String? = null,
    @SerializedName("date_added")
    var date : String? = null,
    @SerializedName("user_id")
    var user_id : String? = null
) : Parcelable
