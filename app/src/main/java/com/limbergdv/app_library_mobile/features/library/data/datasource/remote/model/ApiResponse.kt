package com.limbergdv.app_library_mobile.features.library.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("data")
    val data: T,

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: String
)

// Respuesta para lista de libros
data class BooksListResponse(
    @SerializedName("success")
    val success: Boolean,

    @SerializedName("data")
    val data: List<BookDto>,

    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: String
)