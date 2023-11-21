package com.fdev.weatherdemo.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fdev.weatherdemo.presentation.weatherMain.WeatherDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val DEFAULT_SEARCH_CITY = "Moscow"

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    private val _weatherDataState = MutableStateFlow<WeatherDataState>(WeatherDataState.Loading)
    val weatherDataState = _weatherDataState.asStateFlow()
    var searchFieldValue by mutableStateOf(DEFAULT_SEARCH_CITY)
        private set

    fun updateSearchField(input: String) {
        searchFieldValue = input
    }

    fun searchWeather() {

    }
}