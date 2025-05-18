package com.example.mapapp.Model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class MarkerData(
    val id: String = UUID.randomUUID().toString(), // CLIENTE genera el id
    val title: String,
    val description: String,
    @SerialName("image_uri") val imageUri: String? = null,
    val latitude: Double,
    val longitude: Double
)
