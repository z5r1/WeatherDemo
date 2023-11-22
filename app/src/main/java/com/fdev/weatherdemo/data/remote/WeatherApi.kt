package com.fdev.weatherdemo.data.remote

import javax.inject.Inject

class WeatherApi @Inject constructor(private val api: IWeatherApi) {
    suspend fun getWeatherFewDaysByCityName(cityName: String) =
        api.getWeatherFewDaysByCityName(cityName)
}