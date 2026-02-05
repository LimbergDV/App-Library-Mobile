package com.limbergdv.app_library_mobile.features.library.domain.entities

data class Book(
    val id: String?,
    val title: String,
    val author: String,
    val editorial: String,
    val numberOfPages: Int,
    val urlImage: String?,
    val backgroundColor: String?
)