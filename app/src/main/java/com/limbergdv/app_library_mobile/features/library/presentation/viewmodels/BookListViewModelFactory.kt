package com.limbergdv.app_library_mobile.features.library.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.limbergdv.app_library_mobile.features.library.domain.usecases.GetBooksUseCase

class BookListViewModelFactory (
    private val getBooksUseCase: GetBooksUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BooksListViewModel(getBooksUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}