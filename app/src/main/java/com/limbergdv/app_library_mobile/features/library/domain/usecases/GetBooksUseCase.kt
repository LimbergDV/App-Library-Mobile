package com.limbergdv.app_library_mobile.features.library.domain.usecases

import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.domain.repositories.BookRepository

class GetBooksUseCase(
    private val repository: BookRepository
) {
    suspend operator fun invoke(): Result<List<Book>> {
        return try {
            val result = repository.getBooks()
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}