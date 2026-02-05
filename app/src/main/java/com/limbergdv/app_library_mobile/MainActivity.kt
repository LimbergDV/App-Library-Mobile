package com.limbergdv.app_library_mobile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.limbergdv.app_library_mobile.core.di.AppContainer
import com.limbergdv.app_library_mobile.core.navigation.AuthNavGraph
import com.limbergdv.app_library_mobile.core.navigation.NavigationWrapper
import com.limbergdv.app_library_mobile.core.ui.theme.AppTheme
import com.limbergdv.app_library_mobile.features.library.navigation.BooksNavGraph


class MainActivity : ComponentActivity() {

    lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appContainer = AppContainer(this)

        val authNavGraph = AuthNavGraph(appContainer)
        val booksNavGraph = BooksNavGraph()

        val navGraphs = listOf(
            authNavGraph,
            booksNavGraph
        )

        enableEdgeToEdge()
        setContent {
            AppTheme {
                NavigationWrapper(navGraphs)
            }
        }
    }
}