package com.example.mapapp.View

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myapplication.Models.Nav.Routes

enum class DrawerItem(
    val icon: ImageVector,
    val text: String,
    val route: Routes
) {
    MAP(Icons.Default.LocationOn, "Map", Routes.MapScreen),
    LIST(Icons.Default.Star, "List", Routes.ListScreen),
}
