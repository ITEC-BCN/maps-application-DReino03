package com.example.mapapp.Model

import android.content.Context

class SessionManager(context: Context) {
    // Obtenemos SharedPreferences privado para el archivo "auth_prefs"
    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    // Guardar el estado de sesión (logged in / logged out)
    fun setLoggedIn(isLoggedIn: Boolean) {
        prefs.edit().putBoolean("is_logged_in", isLoggedIn).apply()
    }

    // Leer el estado de sesión (por defecto false)
    fun isLoggedIn(): Boolean {
        return prefs.getBoolean("is_logged_in", false)
    }
}
