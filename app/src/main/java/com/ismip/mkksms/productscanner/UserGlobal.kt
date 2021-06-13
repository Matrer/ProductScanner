package com.ismip.mkksms.productscanner

import android.content.Context
import android.content.Context.MODE_PRIVATE

object UserGlobal {
    private const val PREFS_NAME = "MyPrefsFile"
    private const val PREF_EMAIL = "email"
    private const val PREF_PASSWORD = "password"

    fun save(email: String, password: String, context: Context) {

        context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE)
            .edit()
            .putString(PREF_EMAIL, email)
            .putString(PREF_PASSWORD, password)
            .apply()
    }

    fun getPassword(context: Context): String {
        val pref =
            context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getString(PREF_PASSWORD, null)
        return pref ?: ""
    }

    fun getUserEmail(context: Context): String {
        val pref =
            context.getSharedPreferences(PREFS_NAME, MODE_PRIVATE).getString(PREF_EMAIL, null)
        return pref ?: ""
    }
}
