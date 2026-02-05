package com.limbergdv.app_library_mobile.features.login.presentation.viewmodels

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.limbergdv.app_library_mobile.features.login.presentation.screens.LoginUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel: ViewModel() {
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

        // Validaciones básicas
        if (_uiState.value.email.isEmpty()) {
            _uiState.update { it.copy(emailError = "Ingresa tu correo electrónico") }
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(_uiState.value.email).matches()) {
            _uiState.update { it.copy(emailError = "Ingresa un correo válido") }
            return
        }

        if (_uiState.value.password.isEmpty()) {
            _uiState.update { it.copy(passwordError = "Ingresa tu contraseña") }
            return
        }

        if(currentPassword.length < 7){
            _uiState.update { it.copy(passwordError = "La contraseña debe tener 7 o más caracteres") }
            return
        }

        // TODO: Implementar lógica de login con API
        _uiState.update { it.copy(isLoading = true) }
    }
}