package com.example.mapapp.ViewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mapapp.Model.MarkerData
import com.example.mapapp.Model.supabase
import io.github.jan.supabase.postgrest.postgrest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class MapViewModel : ViewModel() {

    private val _markersFlow = MutableStateFlow<List<MarkerData>>(emptyList())
    val markersFlow: StateFlow<List<MarkerData>> = _markersFlow

    init {
        fetchMarkers()
    }

    fun fetchMarkers() = viewModelScope.launch {
        try {
            // 1) Llamada y obtenemos JSON crudo
            val json: String = supabase.postgrest["markers"]
                .select().toString()
            // 2) Parseamos a lista
            val list: List<MarkerData> = Json.decodeFromString(json)
            _markersFlow.value = list
        } catch (e: Exception) {
            Log.e("MapViewModel", "fetchMarkers failed", e)
        }
    }

    fun addMarker(marker: MarkerData, onResult: (Boolean) -> Unit) = viewModelScope.launch {
        val success = try {
            // No esperamos respuesta; si falla lanza excepción
            supabase.postgrest["markers"].insert(marker)
            true
        } catch (e: Exception) {
            Log.e("MapViewModel", "addMarker failed", e)
            false
        }
        if (success) fetchMarkers()
        onResult(success)
    }

    fun updateMarker(marker: MarkerData, onResult: (Boolean) -> Unit) = viewModelScope.launch {
        val success = try {
            supabase.postgrest["markers"]
                .update(marker) {
                    filter { eq("id", marker.id) }
                }
            true
        } catch (e: Exception) {
            Log.e("MapViewModel", "updateMarker failed", e)
            false
        }
        if (success) fetchMarkers()
        onResult(success)
    }
    fun deleteMarker(id: String, onResult: (Boolean) -> Unit) = viewModelScope.launch {
        val success = try {
            supabase.postgrest["markers"]
                .delete { filter { eq("id", id) } }
            true
        } catch (e: Exception) {
            Log.e("MapViewModel", "deleteMarker failed", e)
            false
        }
        if (success) fetchMarkers()
        onResult(success)
    }
}