package com.example.mapapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import com.example.mapapp.View.MyDrawerMenu
import com.example.mapapp.ui.theme.MapAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MapAppTheme {
                MyDrawerMenu()
            }
        }
    }
}

