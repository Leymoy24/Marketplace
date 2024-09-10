package com.example.marketplace.di.module

import com.example.marketplace.di.scope.AppScope
import com.example.marketplace.domain.AuthRepository
import com.example.marketplace.domain.IsEmailCorrectUseCase
import com.example.marketplace.domain.IsEmailCorrectUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
object DomainModule {

    @Provides
    @AppScope
    fun provideIsEmailCorrect(repository: AuthRepository): IsEmailCorrectUseCase {
        return IsEmailCorrectUseCaseImpl(authRepository = repository)
    }
}