package com.limbergdv.app_library_mobile.features.users.data.datasource.remote.dtos

data class UserDto(
    val success: Boolean,
    val data: Data,
    val message: String,
    val status: String
)

data class Data(
    val id: String,
    val email: String,
)
