package com.fdev.weatherdemo.data

import android.util.Log
import com.fdev.weatherdemo.data.entity.WeatherData
import com.fdev.weatherdemo.data.local.WeatherLocalDataSource
import com.fdev.weatherdemo.data.remote.WeatherApi
import javax.inject.Inject

class Repository @Inject constructor(
    private val weatherApi: WeatherApi,
    private val localDataSource: WeatherLocalDataSource
) {
    suspend fun loadWeatherData(query: String): WeatherData? {
        return try {
            val response = weatherApi.getWeatherDataByCityName(query)
            val weatherData = response.weatherData
            localDataSource.saveWeatherData(weatherData)
            weatherData
        } catch (e: Exception) {
            Log.d(Repository::class.simpleName, e.message.toString())
            localDataSource.getLastWeatherData()
        }
    }
}