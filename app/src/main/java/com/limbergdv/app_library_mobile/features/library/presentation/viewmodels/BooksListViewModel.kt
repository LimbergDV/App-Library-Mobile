package com.limbergdv.app_library_mobile.features.library.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.domain.usecases.GetBooksUseCase
import com.limbergdv.app_library_mobile.features.library.presentation.screens.BooksListUiState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BooksListViewModel(
    private val getBooksUseCase: GetBooksUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(BooksListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        fetchBooks()
    }

    // Cambiamos loadMockBooks por una llamada real
    fun fetchBooks() {
        // 1. Iniciamos carga y limpiamos errores previos
        _uiState.update { it.copy(isLoading = true, error = null) }

        viewModelScope.launch {
            try {
                // 2. Obtenemos el resultado (Result<List<Book>>)
                val result = getBooksUseCase.invoke()

                // 3. Desempaquetamos el resultado fuera del update
                result.fold(
                    onSuccess = { booksList ->
                        _uiState.update {
                            it.copy(
                                books = booksList,
                                isLoading = false
                            )
                        }
                    },
                    onFailure = { exception ->
                        _uiState.update {
                            it.copy(
                                isLoading = false,
                                // Aquí capturamos el mensaje real del error (ej. "No internet", "404")
                                error = exception.message ?: "Error desconocido"
                            )
                        }
                    }
                )
            } catch (e: Exception) {
                // Catch de seguridad por si hay un crash inesperado que no venga encapsulado en Result
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = e.message ?: "Error crítico"
                    )
                }
            }
        }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    // Filtra la lista que ya tenemos en memoria (la que vino de la API)
    fun getFilteredBooks(): List<Book> {
        val currentState = _uiState.value
        val query = currentState.searchQuery.lowercase()

        if (query.isEmpty()) return currentState.books

        return currentState.books.filter {
            it.title.lowercase().contains(query) ||
                    it.author.lowercase().contains(query)
        }
    }
}