package com.fdev.weatherdemo.data.entity

import com.google.gson.annotations.SerializedName

data class Hour(
    @SerializedName("time") val time: String,
    @SerializedName("temp_c") val temp: Float?,
    @SerializedName("wind_kph") val windKph: Float?,
    @SerializedName("condition") val condition: Condition?,
)
