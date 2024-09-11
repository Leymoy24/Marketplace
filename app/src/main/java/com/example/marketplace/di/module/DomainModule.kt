package com.example.marketplace.di.module

import com.example.marketplace.di.scope.AppScope
import com.example.marketplace.domain.AuthRepository
import com.example.marketplace.domain.IsEmailCorrectUseCase
import com.example.marketplace.domain.IsEmailCorrectUseCaseImpl
import com.example.marketplace.domain.LoginUserUseCase
import com.example.marketplace.domain.LoginUserUseCaseImpl
import com.example.marketplace.domain.RegisterUserUseCase
import com.example.marketplace.domain.RegisterUserUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
object DomainModule {

    @Provides
    @AppScope
    fun provideIsEmailCorrect(repository: AuthRepository): IsEmailCorrectUseCase {
        return IsEmailCorrectUseCaseImpl(authRepository = repository)
    }

    @Provides
    @AppScope
    fun provideRegisterUser(repository: AuthRepository): RegisterUserUseCase {
        return RegisterUserUseCaseImpl(authRepository = repository)
    }

    @Provides
    @AppScope
    fun provideLoginUser(repository: AuthRepository): LoginUserUseCase {
        return LoginUserUseCaseImpl(authRepository = repository)
    }

}