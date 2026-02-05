package com.limbergdv.app_library_mobile.core.di

import android.content.Context
import com.limbergdv.app_library_mobile.core.SessionManager
import com.limbergdv.app_library_mobile.core.network.LibraryApi
import com.limbergdv.app_library_mobile.features.auth.data.repositories.AuthRepositoryImpl
import com.limbergdv.app_library_mobile.features.auth.domain.repositories.AuthRepository
import com.limbergdv.app_library_mobile.features.auth.domain.usecases.LoginUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer(context: Context) {

    private val sessionManager = SessionManager(context)

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
            sessionManager.fetchAuthToken()?.let {
                request.addHeader("Authorization", "Bearer $it")
            }
            chain.proceed(request.build())
        }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.aleosh.online/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val libraryApi: LibraryApi by lazy {
        retrofit.create(LibraryApi::class.java)
    }

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(libraryApi)
    }

    val sessionManagerProvider: SessionManager by lazy {
        sessionManager
    }
}