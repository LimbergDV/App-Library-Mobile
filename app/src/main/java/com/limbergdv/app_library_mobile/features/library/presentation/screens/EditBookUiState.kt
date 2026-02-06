package com.limbergdv.app_library_mobile.features.library.presentation.screens

data class EditBookUiState(
    val isLoading: Boolean = false,
    val isBookUpdated: Boolean = false,
    val error: String? = null,
    val id: String? = null,
    val title: String = "",
    val author: String = "",
    val editorial: String = "",
    val pages: String = "",
    val photoUrl: String = "",
    val titleError: String? = null,
    val authorError: String? = null,
    val editorialError: String? = null,
    val pagesError: String? = null
)
