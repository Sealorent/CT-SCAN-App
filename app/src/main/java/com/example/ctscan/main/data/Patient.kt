package com.example.ctscan.main.data

import android.graphics.drawable.Drawable
import android.media.Image
import android.media.ImageReader
import java.util.*

data class Patient(
    var id: Long ? = null,
    var date: String ? = null,
    var name: String ? = null,
    var status: String ? = null,
    var gender: String ? = null,
    var image: Int
)
