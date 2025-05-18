package com.example.mapapp.ViewModel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import com.example.mapapp.Model.SessionManager

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    private val session = SessionManager(application)
    var username by mutableStateOf("")
    var password by mutableStateOf("")
    var errorMsg by mutableStateOf("")

    // Función para procesar el login
    fun performLogin(onSuccess: () -> Unit) {
        if (username == "admin" && password == "1234") {
            session.setLoggedIn(true)
            onSuccess()
        } else {
            errorMsg = "Credenciales inválidas"
        }
    }
}
