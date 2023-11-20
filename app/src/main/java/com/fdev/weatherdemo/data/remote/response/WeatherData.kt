package com.fdev.weatherdemo.data.remote.response

import com.google.gson.annotations.SerializedName

data class WeatherData(
    @SerializedName("wind_mph") val windMph: Float,
)