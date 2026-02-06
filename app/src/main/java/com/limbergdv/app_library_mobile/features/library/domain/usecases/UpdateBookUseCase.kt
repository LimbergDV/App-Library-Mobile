package com.limbergdv.app_library_mobile.features.library.domain.usecases

import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.domain.repositories.BookRepository
import java.io.File

class UpdateBookUseCase(
    private val repository: BookRepository
) {
    suspend operator fun invoke(book: Book, image: File): Result<Book> {
        return try {
            // Validaciones de negocio
            if (book.id.isBlank()) {
                return Result.failure(Exception("El ID del libro es requerido"))
            }

            if (book.title.isBlank()) {
                return Result.failure(Exception("El título es requerido"))
            }

            if (book.author.isBlank()) {
                return Result.failure(Exception("El autor es requerido"))
            }

            if (book.editorial.isBlank()) {
                return Result.failure(Exception("La editorial es requerida"))
            }

            if (book.numberOfPages <= 0) {
                return Result.failure(Exception("El número de páginas debe ser mayor a 0"))
            }

            val result = repository.updateBook(book, image)
            Result.success(result)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
