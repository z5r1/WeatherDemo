package com.fdev.weatherdemo.presentation.weatherMain

import com.fdev.weatherdemo.data.remote.response.WeatherData

sealed interface WeatherDataState {
    data class Success(val forecast: WeatherData?): WeatherDataState
    data class Error(val errorMessage: String?): WeatherDataState

    object Loading: WeatherDataState
}