package com.example.marketplace.di.module

import android.content.Context
import com.example.marketplace.data.network.ApiRoutes
import com.example.marketplace.data.network.ApiService
import com.example.marketplace.data.network.ApiServiceImpl
import com.example.marketplace.data.source.sharedPref.SharedPref
import com.example.marketplace.di.scope.AppScope
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {

    @Provides
    @AppScope
    fun provideLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @AppScope
    fun provideOkHttpClient(logger: HttpLoggingInterceptor, sharedPref: SharedPref): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(logger)
            .addInterceptor { chain ->
                val originalRequest = chain.request()
                val token = sharedPref.getToken()

                val newRequest = if (token.isNotEmpty()) {
                    originalRequest.newBuilder()
                        .addHeader("Authorization", "Bearer $token") // Добавляем токен в заголовок
                        .build()
                } else {
                    originalRequest
                }

                chain.proceed(newRequest)
            }
            .addInterceptor { chain ->
                try {
                    chain.proceed(chain.request())
                } catch (e: SocketTimeoutException) {
                    // Обработка исключения
                    throw java.io.IOException("Превышено время ожидания ответа от сервера", e)
                }
            }
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @AppScope
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(ApiRoutes.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @AppScope
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiServiceImpl::class.java)
    }
}