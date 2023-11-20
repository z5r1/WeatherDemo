package com.fdev.weatherdemo.data.remote

import com.fdev.weatherdemo.data.remote.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IWeatherApi {
    @GET("/current.json/q{lng},{lat}")
    suspend fun getWeatherDataByCoords(
        @Path("lng") latitude: Double,  @Path("lat") longitude: Double,
    ): WeatherResponse

    @GET("/current.json")
    suspend fun getWeatherDataByCityName(
        @Query("q") cityName: String,
    ): WeatherResponse
}