package com.limbergdv.app_library_mobile.features.library.presentation.screens

data class AddBookUiState (
    val title: String = "",
    val author: String = "",
    val editorial: String = "",
    val pages: String = "",
    val photoUrl: String = "",
    val isLoading: Boolean = false,
    val isBookCreated: Boolean = false,
    val error: String? = null,
    val titleError: String? = null,
    val authorError: String? = null,
    val editorialError: String? = null,
    val pagesError: String? = null
)
