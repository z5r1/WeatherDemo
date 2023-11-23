package com.fdev.weatherdemo.presentation.screens.weatherDayDetails

import com.fdev.weatherdemo.data.entity.ForecastData

sealed interface WeatherDayDetailsState {
    class DayDayDetails(val forecastData: ForecastData) : WeatherDayDetailsState
    data object Empty : WeatherDayDetailsState
}