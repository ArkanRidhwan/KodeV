package com.example.extend1.utils

import android.content.Context

private lateinit var preferenceManager: PreferenceManager

@Synchronized
fun getInstance(context: Context): PreferenceManager {
    preferenceManager = PreferenceManager(context)
    return preferenceManager
}