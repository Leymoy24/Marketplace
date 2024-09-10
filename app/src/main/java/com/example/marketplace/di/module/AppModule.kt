package com.example.marketplace.di.module

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

    ): AuthRepository = AuthRepositoryImpl(

    )
}