package com.limbergdv.app_library_mobile.features.library.data.datasource.remote.mapper

import com.limbergdv.app_library_mobile.features.library.data.datasource.remote.model.BookDto
import com.limbergdv.app_library_mobile.features.library.domain.entities.Book

fun BookDto.toDomain(): Book {
    return Book(
        id = this.id,
        title = this.title,
        author = this.author,
        editorial = this.editorial,
        numberOfPages = this.numberOfPages,
        urlImage = this.urlImage,
        backgroundColor = this.backgroundColor
    )
}

fun List<BookDto>.toDomain(): List<Book> {
    return this.map { it.toDomain() }
}