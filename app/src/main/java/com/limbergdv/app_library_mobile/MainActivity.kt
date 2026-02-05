package com.limbergdv.app_library_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.limbergdv.app_library_mobile.core.navigation.NavigationWrapper
import com.limbergdv.app_library_mobile.core.ui.theme.AppTheme
import com.limbergdv.app_library_mobile.features.library.navigation.BooksNavGraph


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navGraphs = listOf(
            BooksNavGraph()
        )

        enableEdgeToEdge()
        setContent {
            AppTheme {
                NavigationWrapper(navGraphs)
            }
        }
    }
}