package com.fdev.weatherdemo.presentation.screens.weatherHome

import com.fdev.weatherdemo.data.entity.WeatherData

sealed interface WeatherHomeState {
    class SuccessWeatherHome(val weatherData: WeatherData): WeatherHomeState
    class Error(val errorMessageId: Int): WeatherHomeState

    object Loading: WeatherHomeState
}