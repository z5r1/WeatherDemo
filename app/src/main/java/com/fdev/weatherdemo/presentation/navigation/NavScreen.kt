package com.fdev.weatherdemo.presentation.navigation

sealed class NavScreen(val route: String) {
    data object HomeScreen : NavScreen(NavRoutes.homeScreen)
    data object WeatherDayDetailsScreen : NavScreen(NavRoutes.weatherDayDetailsScreen)
}
