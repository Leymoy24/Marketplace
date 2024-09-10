package com.example.marketplace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.marketplace.di.component.ActivityComponent
import com.example.marketplace.navigation.Navigation
import com.example.marketplace.ui.theme.MarketplaceTheme
import com.example.marketplace.util.ViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private lateinit var activityComponent: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()

        activityComponent = (applicationContext as App).appComponent.activityComponent().create()
        activityComponent.inject(this)

        setContent {
            MarketplaceTheme {
                val navController = rememberNavController()
                Navigation(
                    navController = navController,
                    applicationContext = applicationContext,
                    activityContext = this,
                    viewModelFactory = viewModelFactory
                )
            }
        }
    }
}