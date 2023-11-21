package com.fdev.weatherdemo.data.entity

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location") val locationData: LocationData?,
    @SerializedName("current") val weatherData: WeatherData?,
    @SerializedName("forecast") val forecast: Forecast?,
)