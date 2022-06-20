package com.example.latihan10

import android.content.Context

class PreferenceManager(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences(Constant.KEY_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun putString(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, "").toString()
    }

    fun putInt(key: String, value: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(key, value)
        editor.apply()
    }

    fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }

    fun removeSinglePreference(key: String) {
        val editor = sharedPreferences.edit()
        editor.remove(key)
        editor.apply()
    }

    fun clearPreference() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}