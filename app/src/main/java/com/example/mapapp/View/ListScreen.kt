package com.example.mapapp.View

import android.graphics.pdf.models.ListItem
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mapapp.ViewModel.MapViewModel

@Composable
fun MarkersListScreen(
    navController: NavController,
    viewModel: MapViewModel
) {
    val context = LocalContext.current
    val markers by viewModel.markersFlow.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchMarkers()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(markers) { marker ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .clickable {
                        navController.navigate("marker_detail/${marker.id}")
                    },
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = marker.title, style = MaterialTheme.typography.titleMedium)
                    Text(text = marker.description, style = MaterialTheme.typography.bodySmall)
                }
            }
        }
    }
}
