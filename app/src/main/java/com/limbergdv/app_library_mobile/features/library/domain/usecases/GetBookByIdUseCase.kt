package com.limbergdv.app_library_mobile.features.library.domain.usecases

import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.domain.repositories.BookRepository

class GetBookByIdUseCase(
    private val repository: BookRepository
) {
    suspend operator fun invoke(bookId: String): Result<Book> {
        return try {
            if (bookId.isBlank()) {
                return Result.failure(Exception("El ID del libro no puede estar vacío"))
            }
            val book = repository.getBookById(bookId)
            Result.success(book)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
