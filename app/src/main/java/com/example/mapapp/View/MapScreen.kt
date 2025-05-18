package com.example.mapapp.View

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun MapsScreen(modifier: Modifier = Modifier, navController: NavController) {
    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(41.4534225, 2.1837151), 17f)
    }

    var markerPosition by remember { mutableStateOf<LatLng?>(null) }

    Column(modifier.fillMaxSize()) {
        GoogleMap(
            cameraPositionState = cameraPositionState,
            onMapLongClick = { latLng ->
                markerPosition = latLng
                // Navegar a pantalla AddMarker con la lat y lng
                navController.navigate("addMarker/${latLng.latitude}/${latLng.longitude}")
            }
        ) {
            markerPosition?.let {
                Marker(state = MarkerState(position = it))
            }
        }
    }
}