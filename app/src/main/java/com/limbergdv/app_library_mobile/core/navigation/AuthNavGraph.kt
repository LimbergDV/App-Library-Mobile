package com.limbergdv.app_library_mobile.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.limbergdv.app_library_mobile.core.di.AppContainer
import com.limbergdv.app_library_mobile.core.navigation.BooksList
import com.limbergdv.app_library_mobile.core.navigation.FeatureNavGraph
import com.limbergdv.app_library_mobile.core.navigation.Login
import com.limbergdv.app_library_mobile.core.navigation.Register
import com.limbergdv.app_library_mobile.features.auth.di.AuthModule
import com.limbergdv.app_library_mobile.features.auth.presentation.screens.LoginScreen
import com.limbergdv.app_library_mobile.features.users.presentation.screens.RegisterScreen
import com.limbergdv.app_library_mobile.features.auth.presentation.viewmodels.LoginViewModelFactory
import com.limbergdv.app_library_mobile.features.users.presentation.viewmodels.RegisterViewModel

class AuthNavGraph(
    private val appContainer: AppContainer
) : FeatureNavGraph {

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navController: NavController
    ) {
        // --- PANTALLA DE LOGIN ---
        navGraphBuilder.composable<Login> {
            // 1. Inyección de dependencias


            LoginScreen(
                factory = AuthModule(appContainer).provideLoginViewModelFactory(),
                onNavigateToRegister = {
                    navController.navigate(Register)
                },
                onLoginSuccess = {
                    // Navegar a la lista de libros y borrar el historial para no volver al login al dar "Atrás"
                    navController.navigate(BooksList) {
                        popUpTo(Login) { inclusive = true }
                    }
                }
            )
        }

        // --- PANTALLA DE REGISTRO ---
        navGraphBuilder.composable<Register> {
            // 1. Inyección de dependencias (Asumiendo que tienes un RegisterUseCase y Factory)
            //val viewModelFactory = RegisterViewModelFactory(appContainer.registerUseCase)

            RegisterScreen(
                //factory = viewModelFactory,
                onNavigateToLogin = {
                    // Volver atrás (al Login)
                    navController.popBackStack()
                },
                //onRegisterSuccess = {
                    // Opcional: Si el registro loguea automático, ir a BooksList
                    // O si solo registra, volver al login:
                //    navController.popBackStack()
               // }
            )
        }
    }
}