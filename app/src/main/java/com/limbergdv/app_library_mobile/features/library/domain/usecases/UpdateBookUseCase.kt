package com.limbergdv.app_library_mobile.features.library.domain.usecases

import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.domain.repositories.BooksRepository
import java.io.File

class UpdateBookUseCase(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(
        id: String,
        title: String,
        author: String,
        editorial: String,
        numberOfPages: Int,
        imageFile: File?
    ): Result<Book> {
        return try {
            // Validaciones de negocio
            if (id.isBlank()) {
                return Result.failure(Exception("El ID del libro es requerido"))
            }

            if (title.isBlank()) {
                return Result.failure(Exception("El título es requerido"))
            }

            if (author.isBlank()) {
                return Result.failure(Exception("El autor es requerido"))
            }

            if (editorial.isBlank()) {
                return Result.failure(Exception("La editorial es requerida"))
            }

            if (numberOfPages <= 0) {
                return Result.failure(Exception("El número de páginas debe ser mayor a 0"))
            }

            // Llamar al repositorio
            repository.updateBook(id, title, author, editorial, numberOfPages, imageFile)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}