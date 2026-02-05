package com.limbergdv.app_library_mobile.features.auth.domain.repositories

import com.limbergdv.app_library_mobile.features.auth.domain.entities.AuthDataLogin
import com.limbergdv.app_library_mobile.features.users.domain.entities.User

interface AuthRepository {
    suspend fun login(user: User): AuthDataLogin
}