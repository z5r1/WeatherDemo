package com.fdev.weatherdemo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fdev.weatherdemo.R
import com.fdev.weatherdemo.domain.GetWeatherUseCase
import com.fdev.weatherdemo.presentation.weatherMain.WeatherDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val DEFAULT_SEARCH_CITY = "Moscow"

@HiltViewModel
class MainViewModel @Inject constructor(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {
    private val _weatherDataState = MutableStateFlow<WeatherDataState>(WeatherDataState.Loading)
    val weatherDataState = _weatherDataState.asStateFlow()

    private val _searchFieldValue = MutableStateFlow(DEFAULT_SEARCH_CITY)
    val searchFieldValue = _searchFieldValue.asStateFlow()

    init {
        searchWeather()
    }

    fun updateSearchField(input: String) {
        _searchFieldValue.value = input
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    fun searchWeather() {
        viewModelScope.launch(Dispatchers.Default) {
            _searchFieldValue
                .debounce(300)
                .filter { data ->
                    return@filter data.isNotEmpty()
                }
                .distinctUntilChanged()
                .mapLatest { query ->
                    _weatherDataState.value = WeatherDataState.Loading
                     getWeatherUseCase.loadWeatherData(query)
                }
                .collect { result ->
                    _weatherDataState.value = if (result != null) {
                        WeatherDataState.Success(result)
                    } else {
                        WeatherDataState.Error(R.string.error_loading)
                    }
                }
        }
    }
}