package com.limbergdv.app_library_mobile.features.library.data.repositories

import com.google.gson.Gson
import com.limbergdv.app_library_mobile.core.network.LibraryApi
import com.limbergdv.app_library_mobile.features.library.data.datasource.remote.mapper.toDomain
import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.domain.repositories.BookRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class BooksRepositoryImpl (
    private val api: LibraryApi
) : BookRepository {

    override suspend fun getBooks(): List<Book> {
        val response = api.getAllBooks()
        return response.data.toDomain()
    }

    override suspend fun getBookById(bookId: String): Book {
        val response = api.getBookById(bookId)
        return response.data.toDomain()
    }

    override suspend fun registerBook(
        book: Book,
        image: File
    ): Book {
        val gson = Gson()
        val bookJsonString = gson.toJson(book)

        val bookRequestBody = bookJsonString.toRequestBody("application/json".toMediaTypeOrNull())

        val requestFile = image.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData(
            "image",       // Nombre del campo que espera el backend
            image.name,    // Nombre del archivo
            requestFile    // El contenido
        )

        val result = api.createBook(bookRequestBody, imagePart)
        return result.data.toDomain()
    }

    override suspend fun updateBook(
        book: Book,
        image: File
    ): Book {
        val gson = Gson()
        val bookJsonString = gson.toJson(book)

        val bookRequestBody = bookJsonString.toRequestBody("application/json".toMediaTypeOrNull())

        val requestFile = image.asRequestBody("image/*".toMediaTypeOrNull())
        val imagePart = MultipartBody.Part.createFormData(
            "image",       // Nombre del campo que espera el backend
            image.name,    // Nombre del archivo
            requestFile    // El contenido
        )

        val result = api.updateBook(bookRequestBody, imagePart)
        return result.data.toDomain()
    }

    override suspend fun deleteBook(bookId: String): Boolean {
        val result = api.deleteBook(bookId)
        return result.success
    }
}