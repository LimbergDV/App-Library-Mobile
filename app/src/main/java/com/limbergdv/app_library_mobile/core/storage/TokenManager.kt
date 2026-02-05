package com.limbergdv.app_library_mobile.core.storage

import android.content.Context
import android.content.SharedPreferences

class TokenManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val USER_TOKEN = "user_token"
    }

    // Guardar token
    fun saveToken(token: String) {
        prefs.edit().putString(USER_TOKEN, token).apply()
    }

    // Obtener token
    fun getToken(): String? {
        return prefs.getString(USER_TOKEN, null)
    }

    // Borrar token (Logout)
    fun clearToken() {
        prefs.edit().remove(USER_TOKEN).apply()
    }
}