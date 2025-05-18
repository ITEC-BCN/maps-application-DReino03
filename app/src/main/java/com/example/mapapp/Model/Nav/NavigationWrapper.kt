package com.example.mapapp.Model.Nav

import AddMarkerScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mapapp.View.MapsScreen
import com.example.mapapp.View.MarkerDetailScreen
import com.example.mapapp.View.MarkersListScreen
import com.example.mapapp.View.PermissionsScreen
import com.example.mapapp.ViewModel.MapViewModel
import com.example.myapplication.Models.Nav.Routes


@Composable
fun NavigationWrapper(navController: NavHostController, modifier: Modifier, viewModel: MapViewModel) {
    NavHost(
        navController = navController,
        startDestination = Routes.PermissionsScreen.route
    ) {
        composable(Routes.PermissionsScreen.route) {
            PermissionsScreen {
                navController.navigate(Routes.MapScreen.route) {
                    popUpTo(Routes.PermissionsScreen.route) { inclusive = true }
                }
            }
        }
        composable("addMarker/{lat}/{lng}") { backStackEntry ->
            val lat = backStackEntry.arguments?.getString("lat")?.toDoubleOrNull()
            val lng = backStackEntry.arguments?.getString("lng")?.toDoubleOrNull()
            if (lat != null && lng != null) {
                AddMarkerScreen( lat, lng, viewModel, navController)
            }
        }
        composable("marker_list") {
            MarkersListScreen(navController, viewModel)
        }

        composable(
            route = "marker_detail/{markerId}",
            arguments = listOf(navArgument("markerId") { type = NavType.LongType })
        ) { backStack ->
            val markerId = backStack.arguments!!.getLong("markerId")
            MarkerDetailScreen(markerId, navController, viewModel)
        }
        composable(Routes.MapScreen.route) { MapsScreen(modifier,navController) }

    }
}