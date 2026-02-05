package com.limbergdv.app_library_mobile.features.library.presentation.viewmodels

import androidx.lifecycle.ViewModel
import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.presentation.screens.EditBookUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class EditBookViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(EditBookUiState())
    val uiState = _uiState.asStateFlow()

    fun loadBook(book: Book) {
        _uiState.update {
            it.copy(
                title = book.title,
                author = book.author,
                editorial = book.editorial,
                pages = book.numberOfPages.toString(),

            )
        }
    }

    fun onTitleChange(title: String) {
        _uiState.update { it.copy(title = title, titleError = null) }
    }

    fun onAuthorChange(author: String) {
        _uiState.update { it.copy(author = author, authorError = null) }
    }

    fun onEditorialChange(editorial: String) {
        _uiState.update { it.copy(editorial = editorial, editorialError = null) }
    }

    fun onPagesChange(pages: String) {
        _uiState.update { it.copy(pages = pages, pagesError = null) }
    }

    fun onPhotoUrlChange(url: String) {
        _uiState.update { it.copy(photoUrl = url) }
    }

    fun validateAndUpdate(): Boolean {
        var hasErrors = false

        if (_uiState.value.title.isBlank()) {
            _uiState.update { it.copy(titleError = "El título es requerido") }
            hasErrors = true
        }

        if (_uiState.value.author.isBlank()) {
            _uiState.update { it.copy(authorError = "El autor es requerido") }
            hasErrors = true
        }

        if (_uiState.value.editorial.isBlank()) {
            _uiState.update { it.copy(editorialError = "La editorial es requerida") }
            hasErrors = true
        }

        if (_uiState.value.pages.isBlank()) {
            _uiState.update { it.copy(pagesError = "Las páginas son requeridas") }
            hasErrors = true
        }

        if (hasErrors) return false

        // TODO: Actualizar libro con API
        _uiState.update { it.copy(isLoading = true) }
        return true
    }
}