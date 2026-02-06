package com.limbergdv.app_library_mobile.features.library.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.domain.usecases.CreateBookUseCase
import com.limbergdv.app_library_mobile.features.library.presentation.screens.AddBookUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID

class AddBookViewModel(
    private val createBookUseCase: CreateBookUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddBookUiState())
    val uiState: StateFlow<AddBookUiState> = _uiState.asStateFlow()

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

    fun onAddBookClicked(image: File?) {
        val state = _uiState.value
        val pagesInt = state.pages.toIntOrNull()

        val titleError = if (state.title.isBlank()) "El título es requerido" else null
        val authorError = if (state.author.isBlank()) "El autor es requerido" else null
        val editorialError = if (state.editorial.isBlank()) "La editorial es requerida" else null
        val pagesError = if (pagesInt == null || pagesInt <= 0) "El número de páginas debe ser mayor a 0" else null
        val generalError = if (image == null) "Debe seleccionar una imagen" else null


        _uiState.update {
            it.copy(
                titleError = titleError,
                authorError = authorError,
                editorialError = editorialError,
                pagesError = pagesError,
                error = generalError
            )
        }

        val hasError = titleError != null || authorError != null || editorialError != null || pagesError != null || generalError != null
        if (hasError) return

        val book = Book(
            id = UUID.randomUUID().toString(),
            title = state.title,
            author = state.author,
            editorial = state.editorial,
            numberOfPages = pagesInt!!,
            urlImage = "", // Se actualizará en la capa de datos
            backgroundColor = "" // Se actualizará en la capa de datos
        )

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            createBookUseCase(book, image!!)
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false, isBookCreated = true) }
                }
                .onFailure { exception ->
                    _uiState.update { it.copy(isLoading = false, error = exception.message) }
                }
        }
    }
}
