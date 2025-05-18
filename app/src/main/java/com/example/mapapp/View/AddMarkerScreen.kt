import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.mapapp.Model.MarkerData
import com.example.mapapp.Model.MediaStoreImageSaver
import com.example.mapapp.ViewModel.MapViewModel
import kotlinx.coroutines.launch
import java.util.*


@Composable
fun AddMarkerScreen(
    lat: Double,
    lng: Double,
    viewModel: MapViewModel,
    navController: NavController
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri -> imageUri = uri }

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { bitmap ->
        bitmap?.let {
            val path = MediaStoreImageSaver.saveBitmapToMediaStore(context, it)
            imageUri = path?.toUri()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
            .padding(top = 16.dp, bottom = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(56.dp))

            Text("Agregar Marcador", style = MaterialTheme.typography.titleLarge)

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

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { cameraLauncher.launch() }) {
                    Icon(Icons.Default.CameraAlt, contentDescription = "Cámara")
                }
                IconButton(onClick = { galleryLauncher.launch("image/*") }) {
                    Icon(Icons.Default.Photo, contentDescription = "Galería")
                }
            }

            imageUri?.let {
                Image(
                    painter = rememberAsyncImagePainter(it),
                    contentDescription = "Imagen seleccionada",
                    modifier = Modifier
                        .height(180.dp)
                        .fillMaxWidth()
                )
            }

            Text("Ubicación: $lat, $lng", style = MaterialTheme.typography.bodySmall)
        }

        Button(
            onClick = {
                val marker = MarkerData(
                    title = title,
                    description = description,
                    imageUri = imageUri?.toString(),
                    latitude = lat,
                    longitude = lng
                )
                scope.launch {
                    viewModel.addMarker(marker) { ok ->
                        if (ok) {
                            Toast.makeText(context, "Marcador guardado", Toast.LENGTH_SHORT).show()
                            navController.popBackStack()
                        } else {
                            Toast.makeText(context, "Error al guardar", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
    }
}
