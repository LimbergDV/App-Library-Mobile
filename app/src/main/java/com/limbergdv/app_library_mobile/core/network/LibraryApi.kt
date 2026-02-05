package com.limbergdv.app_library_mobile.core.network

import retrofit2.http.POST

interface LibraryApi {
    @POST("/users")
    suspend fun createUser()
}