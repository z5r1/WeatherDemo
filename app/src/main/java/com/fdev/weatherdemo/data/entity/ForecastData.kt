package com.fdev.weatherdemo.data.entity

import com.google.gson.annotations.SerializedName

data class ForecastData(
    @SerializedName("date") val date: String,
    @SerializedName("day") val day: Day,
    @SerializedName("hour") val hour: List<Hour>,
)