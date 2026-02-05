package com.limbergdv.app_library_mobile.features.auth.domain.usecases

import com.limbergdv.app_library_mobile.core.SessionManager
import com.limbergdv.app_library_mobile.features.auth.domain.entities.AuthDataLogin
import com.limbergdv.app_library_mobile.features.auth.domain.repositories.AuthRepository
import com.limbergdv.app_library_mobile.features.users.domain.User

class LoginUseCase(
    private val repository: AuthRepository,
    private val sessionManager: SessionManager
) {
    suspend operator fun invoke(user: User): Result<AuthDataLogin> {
        return try {
            val dataLogin = repository.login(user)
            sessionManager.saveAuthToken(dataLogin.token)
            Result.success(dataLogin)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}