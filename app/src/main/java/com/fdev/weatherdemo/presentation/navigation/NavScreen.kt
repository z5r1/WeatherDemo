package com.fdev.weatherdemo.presentation.navigation

sealed class NavScreen(val route: String) {
    object HomeScreen : NavScreen(NavRoutes.homeScreen)
    object WeatherDayDetailsScreen : NavScreen(NavRoutes.weatherDayDetailsScreen)
}
