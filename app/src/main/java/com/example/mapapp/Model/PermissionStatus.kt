package com.example.mapapp.Model

sealed class PermissionStatus {
    object Granted : PermissionStatus()
    object Denied : PermissionStatus()
    object PermanentlyDenied : PermissionStatus()
}
