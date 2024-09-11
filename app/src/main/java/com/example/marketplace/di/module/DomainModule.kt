package com.example.marketplace.di.module

import com.example.marketplace.data.source.SessionStorage
import com.example.marketplace.di.scope.AppScope
import com.example.marketplace.domain.repository.AuthRepository
import com.example.marketplace.domain.repository.MainRepository
import com.example.marketplace.domain.usecase.GetTokenUseCase
import com.example.marketplace.domain.usecase.GetTokenUseCaseImpl
import com.example.marketplace.domain.usecase.IsEmailCorrectUseCase
import com.example.marketplace.domain.usecase.IsEmailCorrectUseCaseImpl
import com.example.marketplace.domain.usecase.LoginUserUseCase
import com.example.marketplace.domain.usecase.LoginUserUseCaseImpl
import com.example.marketplace.domain.usecase.RegisterUserUseCase
import com.example.marketplace.domain.usecase.RegisterUserUseCaseImpl
import com.example.marketplace.domain.usecase.SaveTokenUseCase
import com.example.marketplace.domain.usecase.SaveTokenUseCaseImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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

    @Provides
    @AppScope
    fun provideGetToken(repository: AuthRepository): GetTokenUseCase {
        return GetTokenUseCaseImpl(authRepository = repository)
    }

    @Provides
    @AppScope
    fun provideSaveToken(repository: AuthRepository): SaveTokenUseCase {
        return SaveTokenUseCaseImpl(authRepository = repository)
    }

}