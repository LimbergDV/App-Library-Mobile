package com.limbergdv.app_library_mobile.features.library.presentation.viewmodels


import androidx.lifecycle.ViewModel
import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.presentation.screens.BooksListUiState

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class BooksListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(BooksListUiState())
    val uiState = _uiState.asStateFlow()

    init {
        loadMockBooks()
    }

    private fun loadMockBooks() {
        val mockBooks = listOf(
            Book(
                id = "1",
                title = "El principito",
                author = "Antoine de Saint Raynol & Hitchcock",
                editorial = "Raynol & Hitchcock",
                numberOfPages = 120,
                urlImage = "",
                backgroundColor =  ""
            ),
            Book(
                id = "2",
                title = "Don quijote de la mancha",
                author = "Miguel de Cervantes",
                editorial = "Juan de la Cuesta",
                numberOfPages = 1400,
                urlImage = "",
                backgroundColor =  ""
            ),
            Book(
                id = "3",
                title = "La odisea",
                author = "Homero",
                editorial = "Alianza Editorial",
                numberOfPages = 435,
                urlImage = "",
                backgroundColor =  ""
            )
        )
        _uiState.update { it.copy(books = mockBooks) }
    }

    fun onSearchQueryChange(query: String) {
        _uiState.update { it.copy(searchQuery = query) }
    }

    fun getFilteredBooks(): List<Book> {
        val query = _uiState.value.searchQuery.lowercase()
        if (query.isEmpty()) return _uiState.value.books

        return _uiState.value.books.filter {
            it.title.lowercase().contains(query) ||
                    it.author.lowercase().contains(query)
        }
    }
}