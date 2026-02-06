package com.limbergdv.app_library_mobile.features.library.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.domain.usecases.GetBookByIdUseCase
import com.limbergdv.app_library_mobile.features.library.domain.usecases.UpdateBookUseCase
import com.limbergdv.app_library_mobile.features.library.presentation.screens.EditBookUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File

class EditBookViewModel(
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val updateBookUseCase: UpdateBookUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(EditBookUiState())
    val uiState: StateFlow<EditBookUiState> = _uiState.asStateFlow()

    fun loadBook(bookId: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            getBookByIdUseCase(bookId)
                .onSuccess { book ->
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            id = book.id,
                            title = book.title,
                            author = book.author,
                            editorial = book.editorial,
                            pages = book.numberOfPages.toString(),
                            photoUrl = book.urlImage
                        )
                    }
                }
                .onFailure { exception ->
                    _uiState.update { it.copy(isLoading = false, error = exception.message) }
                }
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

    fun onPhotoSelected(uri: String) {
        _uiState.update { it.copy(photoUrl = uri, error = null) }
    }

    fun onUpdateBookClicked(image: File?) {
        val state = _uiState.value
        val pagesInt = state.pages.toIntOrNull()

        val titleError = if (state.title.isBlank()) "El título es requerido" else null
        val authorError = if (state.author.isBlank()) "El autor es requerido" else null
        val editorialError = if (state.editorial.isBlank()) "La editorial es requerida" else null
        val pagesError = if (pagesInt == null || pagesInt <= 0) "El número de páginas debe ser mayor a 0" else null

        _uiState.update {
            it.copy(
                titleError = titleError,
                authorError = authorError,
                editorialError = editorialError,
                pagesError = pagesError
            )
        }

        val hasError = titleError != null || authorError != null || editorialError != null || pagesError != null
        if (hasError) return

        val book = Book(
            id = state.id!!,
            title = state.title,
            author = state.author,
            editorial = state.editorial,
            numberOfPages = pagesInt!!,
            urlImage = state.photoUrl, // El ViewModel no debe construir la URL
            backgroundColor = "" // No es responsabilidad del ViewModel
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            updateBookUseCase(book, image)
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false, isBookUpdated = true) }
                }
                .onFailure { exception ->
                    _uiState.update { it.copy(isLoading = false, error = exception.message) }
                }
        }
    }
}
