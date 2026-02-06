package com.limbergdv.app_library_mobile.features.library.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.domain.usecases.DeleteBookUseCase
import com.limbergdv.app_library_mobile.features.library.presentation.screens.BookDetailsUiState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookDetailViewModel (
    private val deleteBookUseCase: DeleteBookUseCase
) : ViewModel() {
    private val _uiState = MutableStateFlow(BookDetailsUiState())
    val uiState = _uiState.asStateFlow()

    fun loadBook(book: Book) {
        _uiState.update { it.copy(book = book) }
    }

    fun showDeleteDialog() {
        _uiState.update { it.copy(showDeleteDialog = true) }
    }

    fun hideDeleteDialog() {
        _uiState.update { it.copy(showDeleteDialog = false) }
    }

    fun deleteBook() {
        val currentBook = _uiState.value.book ?: return

        _uiState.update { it.copy(isDeleting = true, showDeleteDialog = false) }

        viewModelScope.launch {
            try {
                val result = deleteBookUseCase.invoke(currentBook.id)

                result.fold(
                    onSuccess = {
                        _uiState.update { it.copy(isDeleting = false, isDeletedSuccess = true) }
                    },
                    onFailure = { error ->
                        _uiState.update {
                            it.copy(isDeleting = false, error = error.message ?: "Error al eliminar")
                        }
                    }
                )
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isDeleting = false, error = e.message ?: "Error desconocido")
                }
            }
        }
    }
}
