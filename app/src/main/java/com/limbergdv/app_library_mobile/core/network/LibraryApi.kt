package com.limbergdv.app_library_mobile.core.network

import com.limbergdv.app_library_mobile.features.auth.data.datasources.remote.dtos.AuthDataLoginDto
import com.limbergdv.app_library_mobile.features.users.data.datasource.remote.dtos.UserDto
import com.limbergdv.app_library_mobile.features.users.domain.entities.User
import retrofit2.http.Body
import retrofit2.http.POST

interface LibraryApi {
    @POST("auth/login")
    suspend fun login(@Body user: User): AuthDataLoginDto

    @POST("users")
    suspend fun createUser(@Body user: User): UserDto
}

