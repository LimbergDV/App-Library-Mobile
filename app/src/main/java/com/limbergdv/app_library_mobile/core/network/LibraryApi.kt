package com.limbergdv.app_library_mobile.core.network

import com.limbergdv.app_library_mobile.features.auth.data.datasources.remote.dtos.AuthDataLoginDto
import com.limbergdv.app_library_mobile.features.library.data.datasource.remote.model.ApiResponse
import com.limbergdv.app_library_mobile.features.library.data.datasource.remote.model.BookDto
import com.limbergdv.app_library_mobile.features.library.data.datasource.remote.model.BooksListResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import com.limbergdv.app_library_mobile.features.users.data.datasource.remote.dtos.UserDto
import com.limbergdv.app_library_mobile.features.users.domain.entities.User
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path

interface LibraryApi {
    @POST("auth/login")
    suspend fun login(@Body user: User): AuthDataLoginDto

    @POST("users")
    suspend fun createUser(@Body user: User): UserDto

    @GET("books")
    suspend fun getAllBooks(): BooksListResponse

    @GET("books/{id}")
    suspend fun getBookById(@Path("id") id: String): ApiResponse<BookDto>

    @Multipart
    @POST("books")
    suspend fun createBook(
        @Part("book") bookJson: RequestBody,
        @Part image: MultipartBody.Part?,
    ): ApiResponse<BookDto>


    @Multipart
    @PUT("books/")
    suspend fun updateBook(
        @Part("book") bookJson: RequestBody,
        @Part image: MultipartBody.Part?,
    ): ApiResponse<BookDto>

    @DELETE("books/{id}")
    suspend fun deleteBook(
        @Path("id") id: String,
    ): ApiResponse<Boolean>

}



