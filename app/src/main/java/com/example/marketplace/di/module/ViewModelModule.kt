package com.example.marketplace.di.module

import com.example.marketplace.di.scope.ActivityScope
import com.example.marketplace.domain.IsEmailCorrectUseCase
import com.example.marketplace.domain.IsEmailCorrectUseCaseImpl
import com.example.marketplace.domain.LoginUserUseCase
import com.example.marketplace.domain.RegisterUserUseCase
import com.example.marketplace.util.ViewModelFactory
import dagger.Module
import dagger.Provides

@Module
object ViewModelModule {
    @Provides
    @ActivityScope
    fun provideViewModelFactory(
        isEmailCorrectUseCase: IsEmailCorrectUseCase,
        registerUserUseCase: RegisterUserUseCase,
        loginUserUseCase: LoginUserUseCase
    ): ViewModelFactory = ViewModelFactory(
        isEmailCorrectUseCase = isEmailCorrectUseCase,
        registerUserUseCase = registerUserUseCase,
        loginUserUseCase = loginUserUseCase
    )
}