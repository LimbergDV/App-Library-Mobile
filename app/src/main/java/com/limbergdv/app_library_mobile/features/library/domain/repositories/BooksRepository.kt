package com.limbergdv.app_library_mobile.features.library.domain.repositories

import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import java.io.File

interface BooksRepository {
    suspend fun getAllBooks(): Result<List<Book>>
    suspend fun getBookById(id: String): Result<Book>
    suspend fun createBook(
        title: String,
        author: String,
        editorial: String,
        numberOfPages: Int,
        imageFile: File?
    ): Result<Book>
    suspend fun updateBook(
        id: String,
        title: String,
        author: String,
        editorial: String,
        numberOfPages: Int,
        imageFile: File?
    ): Result<Book>
    suspend fun deleteBook(id: String): Result<Unit>
}