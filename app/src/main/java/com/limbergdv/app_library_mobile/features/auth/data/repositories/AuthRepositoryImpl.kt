package com.limbergdv.app_library_mobile.features.auth.data.repositories

import com.limbergdv.app_library_mobile.core.network.LibraryApi
import com.limbergdv.app_library_mobile.features.auth.data.datasources.remote.mappers.toDomain
import com.limbergdv.app_library_mobile.features.auth.domain.entities.AuthDataLogin
import com.limbergdv.app_library_mobile.features.auth.domain.repositories.AuthRepository
import com.limbergdv.app_library_mobile.features.users.domain.User

class AuthRepositoryImpl (
    private val api: LibraryApi
) : AuthRepository{

    override suspend fun login(user: User): AuthDataLogin {
        val authDataLoginDto = api.login(user)
        return authDataLoginDto.toDomain()
    }

}