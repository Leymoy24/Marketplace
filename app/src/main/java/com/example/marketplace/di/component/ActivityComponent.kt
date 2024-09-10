package com.example.marketplace.di.component

import com.example.marketplace.MainActivity
import com.example.marketplace.di.module.ViewModelModule
import com.example.marketplace.di.scope.ActivityScope
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = ([ViewModelModule::class]))
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    fun inject(activity: MainActivity)
}