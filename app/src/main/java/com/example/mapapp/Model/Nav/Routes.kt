package com.example.myapplication.Models.Nav

import kotlinx.serialization.Serializable


@Serializable
sealed class Routes(val route: String) {
    object ListScreen : Routes("listScreen")
    object MapScreen : Routes("mapScreen")
    object PermissionsScreen : Routes("permissionScreen")
    object AddMarkerScreen : Routes("addMarkerScreen")
    object DetailMarkerScreen : Routes("marker_detail/{markerId}")
}