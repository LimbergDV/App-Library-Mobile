package com.limbergdv.app_library_mobile.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController


@Composable
fun NavigationWrapper(
    navGraphs: List<FeatureNavGraph>
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = BooksList
    ) {
        navGraphs.forEach { graph ->
            graph.registerGraph(this, navController)
        }
    }
}