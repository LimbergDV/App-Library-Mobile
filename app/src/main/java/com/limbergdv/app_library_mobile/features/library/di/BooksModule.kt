package com.limbergdv.app_library_mobile.features.library.di

import com.limbergdv.app_library_mobile.core.di.AppContainer
import com.limbergdv.app_library_mobile.features.library.domain.usecases.CreateBookUseCase
import com.limbergdv.app_library_mobile.features.library.domain.usecases.DeleteBookUseCase
import com.limbergdv.app_library_mobile.features.library.domain.usecases.GetBookByIdUseCase
import com.limbergdv.app_library_mobile.features.library.domain.usecases.GetBooksUseCase
import com.limbergdv.app_library_mobile.features.library.domain.usecases.UpdateBookUseCase

import com.limbergdv.app_library_mobile.features.library.presentation.viewmodels.BookDetailsViewModelFactory
import com.limbergdv.app_library_mobile.features.library.presentation.viewmodels.AddBookViewModelFactory
import com.limbergdv.app_library_mobile.features.library.presentation.viewmodels.BookListViewModelFactory

class BooksModule (
    private val appContainer: AppContainer
) {

    private fun provaiderGetBooksUseCase(): GetBooksUseCase {
        return GetBooksUseCase(appContainer.bookRepository)
    }

    private fun provaiderCreateBookUseCase(): CreateBookUseCase {
        return CreateBookUseCase(appContainer.bookRepository)
    }

    private fun provaiderDeleteBookUseCase(): DeleteBookUseCase {
        return DeleteBookUseCase(appContainer.bookRepository)
    }

    private fun provaiderGetBookByIdUseCase(): GetBookByIdUseCase {
        return GetBookByIdUseCase(appContainer.bookRepository)
    }

    private fun provaiderUpdateUseCase(): UpdateBookUseCase {
        return UpdateBookUseCase(appContainer.bookRepository)
    }


    //fun provideGetViewModelFactory(): RegisterViewModelFactory {
    //    return RegisterViewModelFactory(providerCreateUserUseCase())
    //}
    fun provideAddBookViewModelFactory(): AddBookViewModelFactory {
        return AddBookViewModelFactory(provaiderCreateBookUseCase())
    }

    fun provideBooksListViewModelFactory(): BookListViewModelFactory {
        return BookListViewModelFactory(provaiderGetBooksUseCase())
    }

    fun provideDetailsViewModelFactory(): BookDetailsViewModelFactory {
        return BookDetailsViewModelFactory(provaiderDeleteBookUseCase())
    }



}