package com.limbergdv.app_library_mobile.features.users.data.datasource.remote.mappers

import com.limbergdv.app_library_mobile.features.users.data.datasource.remote.dtos.UserDto

fun UserDto.toDomain(): Boolean {
    return this.success
}