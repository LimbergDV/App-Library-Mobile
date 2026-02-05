package com.limbergdv.app_library_mobile.features.library.domain.usecases

import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.domain.repositories.BooksRepository
import java.io.File

class CreateBookUseCase(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(
        title: String,
        author: String,
        editorial: String,
        numberOfPages: Int,
        imageFile: File?
    ): Result<Book> {
        return try {
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
            repository.createBook(title, author, editorial, numberOfPages, imageFile)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}