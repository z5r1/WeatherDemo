package com.fdev.weatherdemo.presentation.screens.weatherHome

import com.fdev.weatherdemo.data.entity.ForecastData

interface OnWeatherSelectListener {
    fun onSelectDay(data: ForecastData)
}