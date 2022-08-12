package com.example.extend1.utils

import android.graphics.BitmapFactory
import android.util.Base64
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:loadImageBase64")
fun ImageView.loadImageBase64(base64: String?) {
    if (base64 != null) {
        try {
            val imageBytes = Base64.decode(base64, Base64.DEFAULT)
            val imageDecode = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            Glide.with(context)
                .load(imageDecode)
                .into(this)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}