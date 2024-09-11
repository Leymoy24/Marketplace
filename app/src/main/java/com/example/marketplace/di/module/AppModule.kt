package com.example.marketplace.di.module

import android.content.Context
import com.example.marketplace.data.network.ApiService
import com.example.marketplace.data.repository.AuthRepositoryImpl
import com.example.marketplace.data.source.sharedPref.SharedPref
import com.example.marketplace.data.source.sharedPref.SharedPrefImpl
import com.example.marketplace.di.scope.AppScope
import com.example.marketplace.domain.AuthRepository
import dagger.Module
import dagger.Provides

@Module
object AppModule {

    @Provides
    @AppScope
    fun provideAuthRepositoryImpl(
        apiService: ApiService,
        sharedPref: SharedPref
    ): AuthRepository = AuthRepositoryImpl(
        apiService = apiService,
        sharedPref = sharedPref
    )

    @Provides
    @AppScope
    fun provideSharedPref(
        context: Context
    ): SharedPref = SharedPrefImpl(context = context)

}