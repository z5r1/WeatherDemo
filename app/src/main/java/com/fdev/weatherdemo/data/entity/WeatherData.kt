package com.fdev.weatherdemo.data.entity

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("wind_mph") val windMph: Float,
)