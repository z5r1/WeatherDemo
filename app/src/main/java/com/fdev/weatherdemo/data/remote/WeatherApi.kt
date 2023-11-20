package com.fdev.weatherdemo.data.remote

import javax.inject.Inject

class WeatherApi @Inject constructor(private val api: IWeatherApi) {
    suspend fun getWeatherDataByCoords(latitude: Double, longitude: Double) =
        api.getWeatherDataByCoords(latitude, longitude)

    suspend fun getWeatherDataByCityName(cityName: String) =
        api.getWeatherDataByCityName(cityName)
}