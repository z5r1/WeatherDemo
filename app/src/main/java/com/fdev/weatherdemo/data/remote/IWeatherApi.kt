package com.fdev.weatherdemo.data.remote

import com.fdev.weatherdemo.data.entity.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface IWeatherApi {
    @GET("/forecast.json")
    suspend fun getWeatherFewDaysByCityName(
        @Query("q") cityName: String, @Query("days") days: Int = 30
    ): WeatherData
}