package com.example.marketplace.data.source.sharedPref

import android.content.Context

class SharedPrefImpl(context: Context) : SharedPref {
    private val sharedPreferences =
        context.getSharedPreferences(DEFAULT_SHARED_PREF, Context.MODE_PRIVATE)

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN_KEY, token).apply()
    }

    override fun getToken(): String {
        val token = sharedPreferences.getString(TOKEN_KEY, DEFAULT_STRING) ?: DEFAULT_STRING
        return token
    }

    private companion object {
        const val DEFAULT_SHARED_PREF = "default_shared_prefs"
        const val TOKEN_KEY = "token_key"
        const val DEFAULT_STRING = "default"
    }
}