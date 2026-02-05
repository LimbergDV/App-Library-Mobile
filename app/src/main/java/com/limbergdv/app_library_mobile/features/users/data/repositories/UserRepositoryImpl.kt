package com.limbergdv.app_library_mobile.features.users.data.repositories

import com.limbergdv.app_library_mobile.core.network.LibraryApi
import com.limbergdv.app_library_mobile.features.users.data.datasource.remote.mappers.toDomain
import com.limbergdv.app_library_mobile.features.users.domain.entities.User
import com.limbergdv.app_library_mobile.features.users.domain.repositories.UserRepository

class UserRepositoryImpl (
    private val api: LibraryApi
) : UserRepository {

    override suspend fun createUser(user: User): Boolean {
        val result = api.createUser(user)
        return result.toDomain()
    }
}