package com.limbergdv.app_library_mobile.features.users.domain.repositories

import com.limbergdv.app_library_mobile.features.users.domain.entities.User

interface UserRepository {
    suspend fun createUser(user: User): Boolean
}