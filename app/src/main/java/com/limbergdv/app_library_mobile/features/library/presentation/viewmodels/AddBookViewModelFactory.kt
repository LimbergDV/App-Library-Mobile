package com.limbergdv.app_library_mobile.features.library.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.limbergdv.app_library_mobile.features.library.domain.usecases.CreateBookUseCase

class AddBookViewModelFactory(
    private val createBookUseCase: CreateBookUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddBookViewModel::class.java)) {
            return AddBookViewModel(createBookUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
