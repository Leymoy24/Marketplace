package com.example.marketplace.di.module

import com.example.marketplace.data.network.ApiService
import com.example.marketplace.data.repository.AuthRepositoryImpl
import com.example.marketplace.di.scope.AppScope
import com.example.marketplace.domain.AuthRepository
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    @AppScope
    fun provideAuthRepositoryImpl(
        apiService: ApiService
    ): AuthRepository = AuthRepositoryImpl(
        apiService = apiService
    )
}