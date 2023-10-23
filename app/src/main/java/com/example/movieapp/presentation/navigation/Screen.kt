package com.example.movieapp.presentation.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object Splash : Screen("splash_screen")

}

interface Destinations {
    val route: String

    val routeWithArgs: String
}

object DetailDestination : Destinations {
    val movieIdKey = "movieIdKey"
    override val route: String = "detail_screen"
    override val routeWithArgs: String = "$route/{$movieIdKey}"
    val arguments = listOf(navArgument(movieIdKey) { type = NavType.IntType })
}
