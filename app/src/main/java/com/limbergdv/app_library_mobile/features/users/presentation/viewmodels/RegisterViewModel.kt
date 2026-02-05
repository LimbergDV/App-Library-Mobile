package com.limbergdv.app_library_mobile.features.users.presentation.viewmodels

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.limbergdv.app_library_mobile.features.users.presentation.screens.RegisterUiState
import com.limbergdv.app_library_mobile.features.users.domain.usecases.CreateUserUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import com.limbergdv.app_library_mobile.features.users.domain.entities.User
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {
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
        val currentState = _uiState.value

        // --- Validaciones ---
        if (currentState.email.isEmpty()) {
            _uiState.update { it.copy(emailError = "Ingresa una dirección de correo válida") }
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(currentState.email).matches()) {
            _uiState.update { it.copy(emailError = "Ingresa una dirección de correo válida") }
            return
        }

        if (currentState.password.isEmpty()) {
            _uiState.update { it.copy(passwordError = "Ingresa una contraseña") }
            return
        }

        // Validación extra: longitud mínima (opcional pero recomendada)
        if (currentState.password.length < 6) {
            _uiState.update { it.copy(passwordError = "La contraseña debe tener al menos 6 caracteres") }
            return
        }

        if (currentState.confirmPassword.isEmpty()) {
            _uiState.update { it.copy(confirmPasswordError = "Vuelve a ingresar tu contraseña") }
            return
        }

        if (currentState.password != currentState.confirmPassword) {
            _uiState.update { it.copy(confirmPasswordError = "Las contraseñas no coinciden") }
            return
        }

        // --- Lógica de Registro ---
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                // Creamos el objeto User con los datos del formulario
                val newUser = User(
                    id = "", // El backend o la DB generarán el ID
                    email = currentState.email,
                    password = currentState.password
                )

                val result = createUserUseCase(newUser)

                result.fold(
                    onSuccess = {
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                isRegisterSuccess = true // ¡ÉXITO! Esto disparará la navegación
                            )
                        }
                    },
                    onFailure = { error ->
                        _uiState.update { state ->
                            state.copy(
                                isLoading = false,
                                error = error.message ?: "Error al registrar usuario"
                            )
                        }
                    }
                )
            } catch (e: Exception) {
                _uiState.update { it.copy(isLoading = false, error = e.message) }
            }
        }
    }
}