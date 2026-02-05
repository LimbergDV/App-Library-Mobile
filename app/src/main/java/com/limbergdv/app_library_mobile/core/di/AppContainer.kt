package com.limbergdv.app_library_mobile.core.di

import android.content.Context
import com.limbergdv.app_library_mobile.core.network.LibraryApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppContainer (context: Context) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.aleosh.online/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val libraryApi: LibraryApi by lazy {
        retrofit.create(LibraryApi::class.java)
    }
}