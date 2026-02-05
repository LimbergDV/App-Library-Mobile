package com.limbergdv.app_library_mobile.features.auth.di

import com.limbergdv.app_library_mobile.core.di.AppContainer
import com.limbergdv.app_library_mobile.features.auth.domain.usecases.LoginUseCase
import com.limbergdv.app_library_mobile.features.auth.presentation.viewmodels.LoginViewModelFactory

class AuthModule (
    private val appContainer: AppContainer
) {
    private fun providerLoginUseCase(): LoginUseCase {
        return LoginUseCase(appContainer.authRepository, appContainer.sessionManagerProvider)
    }

    fun provideLoginViewModelFactory(): LoginViewModelFactory {
        return LoginViewModelFactory(providerLoginUseCase())
    }


}