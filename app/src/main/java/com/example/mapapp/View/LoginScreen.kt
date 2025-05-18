package com.example.mapapp.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mapapp.Model.SessionManager
import com.example.myapplication.Models.Nav.Routes

@Composable
fun LoginScreen(navController: NavController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }
    val sessionManager = SessionManager(context)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("admin  1234")
        Text("Por favor ingresa tus credenciales", fontSize = 20.sp)
        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Usuario") },
            singleLine = true
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation()
        )
        Spacer(Modifier.height(16.dp))

        if (errorMsg.isNotEmpty()) {
            Text(errorMsg, color = Color.Red)
            Spacer(Modifier.height(8.dp))
        }

        Button(onClick = {
            // Validación estática de credenciales
            if (username == "admin" && password == "1234") {
                // Login correcto: guardamos sesión y navegamos a Home
                sessionManager.setLoggedIn(true)
                navController.navigate(Routes.MapScreen.route) {
                    popUpTo("login") { inclusive = true }
                }

            } else {
                errorMsg = "Usuario o contraseña incorrectos"
            }
        }) {
            Text("Iniciar Sesión")
        }
    }
}
