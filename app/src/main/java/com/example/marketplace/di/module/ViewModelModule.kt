package com.example.marketplace.di.module

import com.example.marketplace.di.scope.ActivityScope
import com.example.marketplace.domain.repository.MainRepository
import com.example.marketplace.domain.usecase.GetTokenUseCase
import com.example.marketplace.domain.usecase.IsEmailCorrectUseCase
import com.example.marketplace.domain.usecase.LoginUserUseCase
import com.example.marketplace.domain.usecase.RegisterUserUseCase
import com.example.marketplace.domain.usecase.SaveTokenUseCase
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
        loginUserUseCase: LoginUserUseCase,
        getTokenUseCase: GetTokenUseCase,
        saveTokenUseCase: SaveTokenUseCase,
        mainRepository: MainRepository
    ): ViewModelFactory = ViewModelFactory(
        isEmailCorrectUseCase = isEmailCorrectUseCase,
        registerUserUseCase = registerUserUseCase,
        loginUserUseCase = loginUserUseCase,
        getTokenUseCase = getTokenUseCase,
        saveTokenUseCase = saveTokenUseCase,
        mainRepository = mainRepository
    )
}