package com.limbergdv.app_library_mobile.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder

interface FeatureNavGraph {
    fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    )
}