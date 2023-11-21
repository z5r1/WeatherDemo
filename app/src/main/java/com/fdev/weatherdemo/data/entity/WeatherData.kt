package com.fdev.weatherdemo.data.entity

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("location") val locationData: LocationData,
    @SerializedName("current") val currentData: CurrentForecastData,
    @SerializedName("forecast") val forecast: Forecast,
) {
    val nowForecast: ForecastData get() = forecast.days.first()
}