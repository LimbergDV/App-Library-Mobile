package com.limbergdv.app_library_mobile.features.auth.data.datasources.remote.dtos

data class AuthDataLoginDto(
    val success: Boolean,
    val data: Data,
    val message: String,
    val status: String
)

data class Data(
    val token: String
)