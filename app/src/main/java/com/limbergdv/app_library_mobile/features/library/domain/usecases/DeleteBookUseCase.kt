package com.limbergdv.app_library_mobile.features.library.domain.usecases

import com.limbergdv.app_library_mobile.features.library.domain.repositories.BookRepository

class DeleteBookUseCase(
    private val repository: BookRepository
) {
    suspend operator fun invoke(id: String): Result<Boolean> {
        return try {
            if (id.isBlank()) {
                return Result.failure(Exception("El ID del libro es requerido"))
            }

            val result = repository.deleteBook(id)
            Result.success(result)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}