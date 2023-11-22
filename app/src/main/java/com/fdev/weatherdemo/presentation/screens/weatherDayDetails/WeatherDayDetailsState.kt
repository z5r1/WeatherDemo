package com.fdev.weatherdemo.presentation.screens.weatherDayDetails

import com.fdev.weatherdemo.data.entity.ForecastData

sealed interface WeatherDayDetailsState {
    class DayDayDetails(val weatherData: ForecastData) : WeatherDayDetailsState
    object Empty : WeatherDayDetailsState
}