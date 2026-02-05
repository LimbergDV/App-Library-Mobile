package com.limbergdv.app_library_mobile.features.library.presentation.screens

import com.limbergdv.app_library_mobile.features.library.domain.entities.Book

data class BooksListUiState (
    val books: List<Book> = emptyList(),
    val searchQuery: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)