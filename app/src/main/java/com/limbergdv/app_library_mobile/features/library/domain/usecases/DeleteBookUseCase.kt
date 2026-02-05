package com.limbergdv.app_library_mobile.features.library.domain.usecases

import com.limbergdv.app_library_mobile.features.library.domain.repositories.BooksRepository

class DeleteBookUseCase(
    private val repository: BooksRepository
) {
    suspend operator fun invoke(id: String): Result<Unit> {
        return try {
            if (id.isBlank()) {
                return Result.failure(Exception("El ID del libro es requerido"))
            }

            repository.deleteBook(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}