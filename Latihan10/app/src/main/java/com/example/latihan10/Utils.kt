package com.example.latihan10

import android.content.Context

private lateinit var preferenceManager: PreferenceManager

object Utils {

    fun putPreferenceString(context: Context, key: String, value: String) {
        preferenceManager = PreferenceManager(context)
        preferenceManager.putString(key, value)
    }

    fun getPreferenceString(context: Context, key: String): String {
        preferenceManager = PreferenceManager(context)
        return preferenceManager.getString(key)
    }

    fun putPreferenceInt(context: Context, key: String, value: Int) {
        preferenceManager = PreferenceManager(context)
        preferenceManager.putInt(key, value)
    }

    fun getPreferenceInt(context: Context, key: String): Int {
        preferenceManager = PreferenceManager(context)
        return preferenceManager.getInt(key)
    }

    fun clearSinglePreference(context: Context, key: String) {
        preferenceManager = PreferenceManager(context)
        return preferenceManager.removeSinglePreference(key)
    }

    fun removePreference(context: Context) {
        preferenceManager = PreferenceManager(context)
        return preferenceManager.clearPreference()
    }
}