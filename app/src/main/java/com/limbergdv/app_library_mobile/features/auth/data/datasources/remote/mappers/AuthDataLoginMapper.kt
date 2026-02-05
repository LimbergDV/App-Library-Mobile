package com.limbergdv.app_library_mobile.features.auth.data.datasources.remote.mappers

import com.limbergdv.app_library_mobile.features.auth.data.datasources.remote.dtos.AuthDataLoginDto
import com.limbergdv.app_library_mobile.features.auth.domain.entities.AuthDataLogin

fun AuthDataLoginDto.toDomain(): AuthDataLogin {
    return AuthDataLogin(
        token = this.data.token
    )
}