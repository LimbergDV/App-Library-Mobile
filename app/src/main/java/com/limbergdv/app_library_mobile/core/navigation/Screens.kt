package com.limbergdv.app_library_mobile.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object BooksList

@Serializable
data class BookDetail(val bookJson: String)

@Serializable
data class BookEdit(val bookJson: String)

@Serializable
object BookAdd