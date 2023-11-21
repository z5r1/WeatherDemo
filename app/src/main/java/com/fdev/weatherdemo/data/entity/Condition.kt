package com.fdev.weatherdemo.data.entity

import com.google.gson.annotations.SerializedName

data class Condition(
    @SerializedName("text") val text: String?,
    @SerializedName("icon") val url: String?,
) {
    //fix format //cdn.weatherapi.com/weather/64x64/night/116.png
    val icon: String? get() {
        return if (url?.startsWith("//") == true) url.replaceFirst("//", "https://") else url
    }
}