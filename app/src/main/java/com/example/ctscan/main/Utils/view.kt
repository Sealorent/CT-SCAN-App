package com.example.ctscan.main.Utils

import android.widget.ImageView
import com.bumptech.glide.Glide

    fun ImageView.setImageUrl(url: String, isCenterCrop: Boolean) {
        Glide.with(context)
            .load(url)
            .centerCrop()
            .into(this)
    }
