package com.limbergdv.app_library_mobile.features.library.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.limbergdv.app_library_mobile.features.library.domain.usecases.GetBookByIdUseCase
import com.limbergdv.app_library_mobile.features.library.domain.usecases.UpdateBookUseCase

class EditBookViewModelFactory(
    private val getBookByIdUseCase: GetBookByIdUseCase,
    private val updateBookUseCase: UpdateBookUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EditBookViewModel::class.java)) {
            return EditBookViewModel(getBookByIdUseCase, updateBookUseCase) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
