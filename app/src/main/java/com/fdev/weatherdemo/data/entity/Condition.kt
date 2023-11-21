package com.fdev.weatherdemo.data.entity

import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("text") val text: String?,
    @SerializedName("icon") val icon: String?,
)