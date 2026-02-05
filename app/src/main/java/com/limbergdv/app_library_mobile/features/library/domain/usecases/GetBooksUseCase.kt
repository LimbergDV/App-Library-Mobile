package com.limbergdv.app_library_mobile.features.library.domain.usecases

import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.domain.repositories.BooksRepository

class GetBooksUseCase(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(): Result<List<Book>> {
        return try {
            val result = repository.getAllBooks()

            result.fold(
                onSuccess = { books ->
                    if (books.isEmpty()) {
                        Result.success(emptyList())
                    } else {
                        Result.success(books)
                    }
                },
                onFailure = { exception ->
                    Result.failure(exception)
                }
            )
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}