package com.fdev.weatherdemo.data.entity

import com.google.gson.annotations.SerializedName

data class Forecast(
    @SerializedName("forecastday") val days: List<ForecastData>
)