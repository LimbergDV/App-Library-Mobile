package com.limbergdv.app_library_mobile.features.auth.presentation.screens

data class LoginUiState (
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val emailError: String? = null,
    val passwordError: String? = null,
    val token: String? = null
)