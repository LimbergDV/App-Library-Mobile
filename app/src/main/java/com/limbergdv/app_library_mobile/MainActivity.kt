package com.limbergdv.app_library_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.limbergdv.app_library_mobile.core.theme.AppTheme
import com.limbergdv.app_library_mobile.features.library.presentation.screeens.LoginScreen
import com.limbergdv.app_library_mobile.features.library.presentation.screeens.RegisterScreen


class MainActivity : ComponentActivity() {
    //lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppTheme {
                RegisterScreen()
            }
        }
    }
}