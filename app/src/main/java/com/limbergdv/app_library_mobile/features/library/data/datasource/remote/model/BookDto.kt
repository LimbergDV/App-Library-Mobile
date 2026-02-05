package com.limbergdv.app_library_mobile.features.library.data.datasource.remote.model

import com.google.gson.annotations.SerializedName

data class BookDto(
    @SerializedName("id")
    val id: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("author")
    val author: String,

    @SerializedName("editorial")
    val editorial: String,

    @SerializedName("numberOfPages")
    val numberOfPages: Int,

    @SerializedName("urlImage")
    val urlImage: String,

    @SerializedName("backgroundColor")
    val backgroundColor: String
)