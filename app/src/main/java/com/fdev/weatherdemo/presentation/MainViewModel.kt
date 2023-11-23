package com.fdev.weatherdemo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdev.weatherdemo.R
import com.fdev.weatherdemo.data.entity.ForecastData
import com.fdev.weatherdemo.domain.GetWeatherUseCase
import com.fdev.weatherdemo.presentation.screens.weatherHome.WeatherHomeState
import com.fdev.weatherdemo.presentation.screens.weatherDayDetails.WeatherDayDetailsState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val DEFAULT_SEARCH_CITY = "Москва"
private const val DEFAULT_SEARCH_BOUNCE = 300L

@HiltViewModel
class MainViewModel @Inject constructor(private val getWeatherUseCase: GetWeatherUseCase) :
    ViewModel() {
    private val _weatherHomeState = MutableStateFlow<WeatherHomeState>(WeatherHomeState.Loading)
    val weatherDataState = _weatherHomeState.asStateFlow()

    private val _forecastDayDataState =
        MutableStateFlow<WeatherDayDetailsState>(WeatherDayDetailsState.Empty)
    val forecastDayDataState = _forecastDayDataState.asStateFlow()

    private val _searchFieldValue = MutableStateFlow(DEFAULT_SEARCH_CITY)
    val searchFieldValue = _searchFieldValue.asStateFlow()

    fun updateSearchField(input: String) {
        _searchFieldValue.value = input
    }

    fun saveDayInfo(data: ForecastData) {
        _forecastDayDataState.value = WeatherDayDetailsState.DayDayDetails(data)
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    fun searchWeather() {
        val flow = _searchFieldValue
            .debounce(DEFAULT_SEARCH_BOUNCE)
            .filter { data ->
                return@filter data.isNotEmpty()
            }
            .distinctUntilChanged()
            .mapLatest { query ->
                _weatherHomeState.value = WeatherHomeState.Loading
                getWeatherUseCase.loadWeatherData(query)
            }

        viewModelScope.launch {
            flow.collect { result ->
                _weatherHomeState.value = if (result != null) {
                    WeatherHomeState.SuccessWeatherHome(result)
                } else {
                    WeatherHomeState.Error(R.string.error_loading)
                }
            }
        }
    }
}