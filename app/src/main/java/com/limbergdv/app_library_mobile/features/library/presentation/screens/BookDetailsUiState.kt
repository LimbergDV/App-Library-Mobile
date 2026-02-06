package com.limbergdv.app_library_mobile.features.library.presentation.screens

import com.limbergdv.app_library_mobile.features.library.domain.entities.Book

data class BookDetailsUiState(
    val book: Book? = null,
    val showDeleteDialog: Boolean = false,
    val isDeleting: Boolean = false,
    val isDeletedSuccess: Boolean = false,
    val error: String? = null
)
