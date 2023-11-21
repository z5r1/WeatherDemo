package com.fdev.weatherdemo.data.remote

import com.fdev.weatherdemo.data.entity.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IWeatherApi {
    @GET("/current.json/q{lng},{lat}")
    suspend fun getCurrentWeatherDataByCoords(
        @Path("lng") latitude: Double,  @Path("lat") longitude: Double,
    ): WeatherResponse

    @GET("/current.json")
    suspend fun getCurrentWeatherDataByCityName(
        @Query("q") cityName: String,
    ): WeatherResponse

    @GET("/forecast.json")
    suspend fun getWeatherDataByCityName(
        @Query("q") cityName: String, @Query("days") days: Int = 3
    ): WeatherResponse
}