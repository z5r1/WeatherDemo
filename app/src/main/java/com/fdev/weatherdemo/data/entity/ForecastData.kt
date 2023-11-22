package com.fdev.weatherdemo.data.entity

import com.fdev.weatherdemo.utils.DateHelper
import com.google.gson.annotations.SerializedName
import java.util.Calendar

data class ForecastData(
    @SerializedName("date") val date: String,
    @SerializedName("day") val day: Day,
    @SerializedName("hour") val hour: List<Hour>,
)