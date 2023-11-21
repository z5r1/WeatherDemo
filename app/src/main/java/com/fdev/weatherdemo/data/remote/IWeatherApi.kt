package com.fdev.weatherdemo.data.remote

import com.fdev.weatherdemo.data.entity.WeatherData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IWeatherApi {
    @GET("/current.json/q{lng},{lat}")
    suspend fun getCurrentWeatherDataByCoords(
        @Path("lng") latitude: Double,  @Path("lat") longitude: Double,
    ): WeatherData

    @GET("/current.json")
    suspend fun getCurrentWeatherDataByCityName(
        @Query("q") cityName: String,
    ): WeatherData

    @GET("/forecast.json")
    suspend fun getWeatherFewDaysByCityName(
        @Query("q") cityName: String, @Query("days") days: Int = 30
    ): WeatherData
}