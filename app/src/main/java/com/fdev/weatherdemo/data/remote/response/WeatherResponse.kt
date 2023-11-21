package com.fdev.weatherdemo.data.remote.response

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("location") val locationData: LocationData,
    @SerializedName("current") val weatherData: WeatherData
)