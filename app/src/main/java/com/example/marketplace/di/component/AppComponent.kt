package com.example.marketplace.di.component

import android.content.Context
import com.example.marketplace.di.module.AppModule
import com.example.marketplace.di.module.DatabaseModule
import com.example.marketplace.di.module.DomainModule
import com.example.marketplace.di.module.NetworkModule
import com.example.marketplace.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(modules = [AppModule::class, NetworkModule::class, DomainModule::class, DatabaseModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun activityComponent(): ActivityComponent.Factory
}