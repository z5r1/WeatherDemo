package com.fdev.weatherdemo.data.local

import android.content.SharedPreferences
import androidx.core.content.edit
import com.fdev.weatherdemo.data.entity.WeatherData
import com.google.gson.Gson
import javax.inject.Inject

private const val WEATHER_DATA_PREF = "WEATHER_DATA_PREF"

//Your database could be here somewhere
class WeatherLocalDataSource @Inject constructor(
    private val prefs: SharedPreferences,
) {
    var lastWeatherData: WeatherData?
        get() {
            val data = prefs.getString(WEATHER_DATA_PREF, null)
            return if (!data.isNullOrEmpty()) Gson().fromJson(
                data,
                WeatherData::class.java
            ) else null
        }
        set(data) {
            prefs.edit {
                putString(WEATHER_DATA_PREF, Gson().toJson(data))
            }
        }
}