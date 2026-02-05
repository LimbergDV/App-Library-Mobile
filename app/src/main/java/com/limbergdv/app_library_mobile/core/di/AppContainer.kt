package com.limbergdv.app_library_mobile.core.di

import android.content.Context
import com.limbergdv.app_library_mobile.core.network.AuthInterceptor
import com.limbergdv.app_library_mobile.core.network.LibraryApi
import com.limbergdv.app_library_mobile.core.storage.TokenManager
import com.limbergdv.app_library_mobile.features.auth.data.repositories.AuthRepositoryImpl
import com.limbergdv.app_library_mobile.features.auth.domain.repositories.AuthRepository
import com.limbergdv.app_library_mobile.features.library.data.repositories.BooksRepositoryImpl
import com.limbergdv.app_library_mobile.features.library.domain.repositories.BooksRepository
import com.limbergdv.app_library_mobile.features.users.data.repositories.UserRepositoryImpl
import com.limbergdv.app_library_mobile.features.users.domain.repositories.UserRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {

    val tokenManager: TokenManager by lazy {
        TokenManager(context)
    }

    private val okHttpClient: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenManager))
            .build()
    }

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api1.aleosh.online/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val libraryApi: LibraryApi by lazy {
        retrofit.create(LibraryApi::class.java)
    }

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(libraryApi, tokenManager)
    }

    val userRepository: UserRepository by lazy {
        UserRepositoryImpl(libraryApi)
    }

}