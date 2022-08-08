package com.example.extend1.utils

import android.content.Context
import android.view.View
import android.widget.EditText
import android.widget.Toast

private lateinit var preferenceManager: PreferenceManager

@Synchronized
fun getInstance(context: Context): PreferenceManager {
    preferenceManager = PreferenceManager(context)
    return preferenceManager
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}

fun EditText.error(text: String) {
    error = text
    requestFocus()
}