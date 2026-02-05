package com.limbergdv.app_library_mobile.features.library.domain.repository

import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.domain.entities.ImageFile

interface BookRepository {
    suspend fun getBooks(): List<Book>
    suspend fun registerBook(book: Book, image: ImageFile): Book
    suspend fun updateBook(book: Book, image: ImageFile?): Book
    suspend fun deleteBook(bookId: String)
}
