package com.limbergdv.app_library_mobile.features.register.presentation.viewmodels

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.limbergdv.app_library_mobile.features.library.presentation.screens.RegisterUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState = _uiState.asStateFlow()

    fun onEmailChange(email: String) {
        _uiState.update { it.copy(email = email, emailError = null) }
    }

    fun onPasswordChange(password: String) {
        _uiState.update { it.copy(password = password, passwordError = null) }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        _uiState.update { it.copy(confirmPassword = confirmPassword, confirmPasswordError = null) }
    }

    fun onRegisterClick() {
        // Validaciones básicas
        if (_uiState.value.email.isEmpty()) {
            _uiState.update { it.copy(emailError = "Ingresa una dirección de correo válida") }
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(_uiState.value.email).matches()) {
            _uiState.update { it.copy(emailError = "Ingresa una dirección de correo válida") }
            return
        }

        if (_uiState.value.password.isEmpty()) {
            _uiState.update { it.copy(passwordError = "Ingresa una contraseña") }
            return
        }

        if (_uiState.value.confirmPassword.isEmpty()) {
            _uiState.update { it.copy(confirmPasswordError = "Vuelve a ingresar tu contraseña") }
            return
        }

        if (_uiState.value.password != _uiState.value.confirmPassword) {
            _uiState.update { it.copy(confirmPasswordError = "Las contraseñas no coinciden") }
            return
        }

        // TODO: Implementar lógica de registro con API
        _uiState.update { it.copy(isLoading = true) }
    }
}