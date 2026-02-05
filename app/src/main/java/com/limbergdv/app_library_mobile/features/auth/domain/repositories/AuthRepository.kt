package com.limbergdv.app_library_mobile.features.auth.domain.repositories

import com.limbergdv.app_library_mobile.features.auth.domain.entities.AuthDataLogin
import com.limbergdv.app_library_mobile.features.users.domain.User

interface AuthRepository {
    suspend fun login(user: User): AuthDataLogin
}