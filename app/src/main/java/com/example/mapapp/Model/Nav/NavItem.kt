package com.example.myapplication.Models.Nav

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val route: Routes,
    val index: Int
)
