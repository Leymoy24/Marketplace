package com.example.marketplace.data.repository

import com.example.marketplace.data.source.RegexManager
import com.example.marketplace.domain.AuthRepository

class AuthRepositoryImpl() : AuthRepository {
    override fun isEmailCorrect(email: String): Boolean {
        return RegexManager.isEmailCorrect(email = email)
    }
}