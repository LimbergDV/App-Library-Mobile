package com.limbergdv.app_library_mobile.features.auth.presentation.viewmodels

import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limbergdv.app_library_mobile.features.auth.domain.usecases.LoginUseCase
import com.limbergdv.app_library_mobile.features.auth.presentation.screens.LoginUiState
import com.limbergdv.app_library_mobile.features.users.domain.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, emailError = null) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, passwordError = null) }
    }

    fun onLoginClick() {
        val currentPassword = _uiState.value.password
        val currentEmail = _uiState.value.email

        // Validaciones básicas
        if (currentEmail.isEmpty()) {
            _uiState.update { it.copy(emailError = "Ingresa tu correo electrónico") }
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(currentEmail).matches()) {
            _uiState.update { it.copy(emailError = "Ingresa un correo válido") }
            return
        }

        if (currentPassword.isEmpty()) {
            _uiState.update { it.copy(passwordError = "Ingresa tu contraseña") }
            return
        }

        if (currentPassword.length < 2) {
            _uiState.update { it.copy(passwordError = "La contraseña debe tener 7 o más caracteres") }
            return
        }

        _uiState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result = loginUseCase(User(id = "", email = currentEmail, password = currentPassword))
            result.fold(
                onSuccess = { data ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            token = data.token
                        )
                    }
                },
                onFailure = { error ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = error.message
                        )
                    }
                }
            )
        }
    }
}