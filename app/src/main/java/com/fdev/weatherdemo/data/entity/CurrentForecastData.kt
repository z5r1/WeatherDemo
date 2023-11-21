package com.fdev.weatherdemo.data.entity

import com.google.gson.annotations.SerializedName

data class CurrentForecastData(
    @SerializedName("temp_c") val temp: Float,
    @SerializedName("condition") val condition: Condition?,
)