package com.limbergdv.app_library_mobile.features.users.domain.usecases

import com.limbergdv.app_library_mobile.features.users.domain.entities.User
import com.limbergdv.app_library_mobile.features.users.domain.repositories.UserRepository

class CreateUserUseCase (
    private val repository: UserRepository,
) {

    suspend operator fun invoke(user: User): Result<Boolean> {
        return try {
            val result = repository.createUser(user)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}