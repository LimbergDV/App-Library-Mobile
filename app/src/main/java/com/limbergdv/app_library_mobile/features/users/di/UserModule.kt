package com.limbergdv.app_library_mobile.features.users.di

import com.limbergdv.app_library_mobile.core.di.AppContainer
import com.limbergdv.app_library_mobile.features.users.domain.usecases.CreateUserUseCase
import com.limbergdv.app_library_mobile.features.users.presentation.viewmodels.RegisterViewModel
import com.limbergdv.app_library_mobile.features.users.presentation.viewmodels.RegisterViewModelFactory

class UserModule (
    private val appContainer: AppContainer
) {

    private fun providerCreateUserUseCase(): CreateUserUseCase {
        return CreateUserUseCase(appContainer.userRepository)
    }

    fun provideRegisterViewModelFactory(): RegisterViewModelFactory {
        return RegisterViewModelFactory(providerCreateUserUseCase())
    }

}