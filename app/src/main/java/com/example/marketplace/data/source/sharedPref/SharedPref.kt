package com.example.marketplace.data.source.sharedPref

interface SharedPref {
    fun saveToken(token: String)
    fun getToken(): String
}