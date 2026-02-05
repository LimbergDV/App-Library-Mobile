package com.limbergdv.app_library_mobile.features.library.domain.repositories

import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import java.io.File

interface BookRepository {
    suspend fun getBooks(): List<Book>
    suspend fun getBookById(bookId: String): Book
    suspend fun registerBook(book: Book, image: File): Book
    suspend fun updateBook(book: Book, image: File): Book
    suspend fun deleteBook(bookId: String)
}
