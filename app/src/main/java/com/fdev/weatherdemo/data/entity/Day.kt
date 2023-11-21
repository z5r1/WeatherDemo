package com.fdev.weatherdemo.data.entity

import com.google.gson.annotations.SerializedName

data class Day(
    @SerializedName("maxtemp_c") val maxTemp: Float?,
    @SerializedName("mintemp_c") val minTemp: Float?,
    @SerializedName("avgtemp_c") val avgTemp: Float?,
    @SerializedName("maxwind_kph") val maxwindKph: Float?,
    @SerializedName("avghumidity") val avghumidity: Float?,
    @SerializedName("condition") val condition: Condition?,
)
