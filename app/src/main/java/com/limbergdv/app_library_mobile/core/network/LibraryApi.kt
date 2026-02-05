package com.limbergdv.app_library_mobile.core.network

import com.limbergdv.app_library_mobile.features.auth.data.datasources.remote.dtos.AuthDataLoginDto
import com.limbergdv.app_library_mobile.features.library.data.datasource.remote.model.ApiResponse
import com.limbergdv.app_library_mobile.features.library.data.datasource.remote.model.BookDto
import com.limbergdv.app_library_mobile.features.library.data.datasource.remote.model.BooksListResponse
import com.limbergdv.app_library_mobile.features.users.domain.User
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface LibraryApi {
    @POST("auth/login")
    suspend fun login(@Body user: User): AuthDataLoginDto

    @GET("books")
    suspend fun getAllBooks(
        @Header("Authorization") token: String
    ): Response<BooksListResponse>

    @GET("books/{id}")
    suspend fun getBookById(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Response<ApiResponse<BookDto>>

    @Multipart
    @POST("books")
    suspend fun createBook(
        @Part("book") bookJson: RequestBody,
        @Part image: MultipartBody.Part?,
        @Header("Authorization") token: String
    ): Response<ApiResponse<BookDto>>


    @Multipart
    @PUT("books/{id}")
    suspend fun updateBook(
        @Path("id") id: String,
        @Part("book") bookJson: RequestBody,
        @Part image: MultipartBody.Part?,
        @Header("Authorization") token: String
    ): Response<ApiResponse<BookDto>>

    @DELETE("books/{id}")
    suspend fun deleteBook(
        @Path("id") id: String,
        @Header("Authorization") token: String
    ): Response<ApiResponse<Unit>>
}