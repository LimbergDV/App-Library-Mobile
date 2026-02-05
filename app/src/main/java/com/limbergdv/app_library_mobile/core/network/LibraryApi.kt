package com.limbergdv.app_library_mobile.core.network

import com.limbergdv.app_library_mobile.features.auth.data.datasources.remote.dtos.AuthDataLoginDto
import com.limbergdv.app_library_mobile.features.users.domain.User
import retrofit2.http.Body
import retrofit2.http.POST

interface LibraryApi {
    @POST("auth/login")
    suspend fun login(@Body user: User): AuthDataLoginDto
}