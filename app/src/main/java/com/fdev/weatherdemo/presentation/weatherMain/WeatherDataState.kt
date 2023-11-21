package com.fdev.weatherdemo.presentation.weatherMain

import com.fdev.weatherdemo.data.entity.WeatherData

sealed interface WeatherDataState {
    class Success(val weatherData: WeatherData): WeatherDataState
    class Error(val errorMessageId: Int): WeatherDataState

    object Loading: WeatherDataState
}