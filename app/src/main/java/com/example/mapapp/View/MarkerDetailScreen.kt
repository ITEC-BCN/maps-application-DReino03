package com.example.mapapp.View

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mapapp.Model.MarkerData
import com.example.mapapp.ViewModel.MapViewModel

@Composable
fun MarkerDetailScreen(
    markerId: Long,
    navController: NavController,
    viewModel: MapViewModel
) {
    val context = LocalContext.current

    // Obtener la lista y buscar el original
    val markers by viewModel.markersFlow.collectAsState()
    val original = markers.find { it.id == markerId.toString() }
        ?: return Text("Marcador no encontrado", modifier = Modifier.padding(24.dp))

    var title by remember { mutableStateOf(original.title) }
    var description by remember { mutableStateOf(original.description) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Detalle del Marcador", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = {
                val updated = original.copy(title = title, description = description)
                viewModel.updateMarker(updated) { success ->
                    if (success) {
                        Toast.makeText(context, "Marcador modificado", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    } else {
                        Toast.makeText(context, "Error al modificar", Toast.LENGTH_SHORT).show()
                    }
                }
            }) {
                Text("Guardar cambios")
            }

            Button(
                onClick = {
                    viewModel.deleteMarker(markerId.toString()) { success ->
                        if (success) {
                            Toast.makeText(context, "Marcador eliminado", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Error al eliminar", Toast.LENGTH_SHORT).show()
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error)
            ) {
                Text("Eliminar")
            }
        }
    }
}
