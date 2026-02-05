package com.limbergdv.app_library_mobile.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.limbergdv.app_library_mobile.core.di.AppContainer
import com.limbergdv.app_library_mobile.features.auth.di.AuthModule
import com.limbergdv.app_library_mobile.features.auth.presentation.screens.LoginScreen
import com.limbergdv.app_library_mobile.features.users.presentation.screens.RegisterScreen
import com.limbergdv.app_library_mobile.features.users.di.UserModule

class AuthNavGraph(
    private val appContainer: AppContainer
) : FeatureNavGraph {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {

        navGraphBuilder.composable<Login> {

            LoginScreen(
                factory = AuthModule(appContainer).provideLoginViewModelFactory(),
                onNavigateToRegister = {
                    navController.navigate(Register)
                },
                onLoginSuccess = {
                    navController.navigate(BooksList) {
                        popUpTo(Login) { inclusive = true }
                    }
                }
            )
        }


        navGraphBuilder.composable<Register> {
            RegisterScreen(
                factory = UserModule(appContainer).provideRegisterViewModelFactory(),
                onNavigateToLogin = {
                    navController.popBackStack()
                },
            )
        }
    }
}