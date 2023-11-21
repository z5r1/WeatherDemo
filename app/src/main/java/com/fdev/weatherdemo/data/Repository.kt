package com.fdev.weatherdemo.data

import com.fdev.weatherdemo.data.remote.response.WeatherData
import kotlinx.coroutines.flow.flow

class Repository {
    fun loadWeatherData(query: String?) = flow<WeatherData?> {

    }
}