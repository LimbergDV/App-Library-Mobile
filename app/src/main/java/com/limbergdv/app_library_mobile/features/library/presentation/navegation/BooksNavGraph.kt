package com.limbergdv.app_library_mobile.features.library.presentation.navegation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.google.gson.Gson
import com.limbergdv.app_library_mobile.core.di.AppContainer
import com.limbergdv.app_library_mobile.core.navigation.BookAdd
import com.limbergdv.app_library_mobile.core.navigation.BookDetail
import com.limbergdv.app_library_mobile.core.navigation.BookEdit
import com.limbergdv.app_library_mobile.core.navigation.BooksList
import com.limbergdv.app_library_mobile.core.navigation.FeatureNavGraph
import com.limbergdv.app_library_mobile.features.library.di.BooksModule
import com.limbergdv.app_library_mobile.features.library.domain.entities.Book
import com.limbergdv.app_library_mobile.features.library.presentation.screens.AddBookScreen
import com.limbergdv.app_library_mobile.features.library.presentation.screens.BookDetailScreen
import com.limbergdv.app_library_mobile.features.library.presentation.screens.BooksListScreen
import com.limbergdv.app_library_mobile.features.library.presentation.screens.EditBookScreen

class BooksNavGraph (
    private val appContainer: AppContainer
) : FeatureNavGraph {

    val booksModule: BooksModule = BooksModule(appContainer)

    override fun registerGraph(navGraphBuilder: NavGraphBuilder, navController: NavController) {

        navGraphBuilder.composable<BooksList> {
            BooksListScreen(
                factory = booksModule.provideBooksListViewModelFactory(),
                onNavigateToAddBook = {
                    navController.navigate(BookAdd)
                },
                onNavigateToBookDetail = { book ->
                    val bookJson = Gson().toJson(book)
                    navController.navigate(BookDetail(bookJson = bookJson))
                }
            )
        }

        // Pantalla: Añadir libro
        navGraphBuilder.composable<BookAdd> {
            AddBookScreen(
                onNavigateBack = {
                    navController.navigateUp()
                },
                onCreatedSuccess = {
                    navController.navigate(BooksList) {
                        popUpTo(BooksList) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla: Detalles del libro
        navGraphBuilder.composable<BookDetail> {
            val args = it.toRoute<BookDetail>()
            val book = Gson().fromJson(args.bookJson, Book::class.java)

            BookDetailScreen(
                factory = booksModule.provideDetailsViewModelFactory(),
                book = book,
                onNavigateBack = {
                    navController.navigateUp()
                },
                onNavigateToEdit = { bookToEdit ->
                    val bookJson = Gson().toJson(bookToEdit)
                    navController.navigate(BookEdit(bookJson = bookJson))
                },
                onDeleteSuccess = {
                    navController.navigate(BooksList) {
                        popUpTo(BooksList) { inclusive = true }
                    }
                }
            )
        }

        // Pantalla: Editar libro
        navGraphBuilder.composable<BookEdit> {
            val args = it.toRoute<BookEdit>()
            val book = Gson().fromJson(args.bookJson, Book::class.java)

            EditBookScreen(
                bookId = book.id,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}