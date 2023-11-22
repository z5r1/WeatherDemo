package com.fdev.weatherdemo.presentation.screens.weatherDayDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fdev.weatherdemo.R
import com.fdev.weatherdemo.data.entity.ForecastData
import com.fdev.weatherdemo.data.entity.Hour
import com.fdev.weatherdemo.presentation.MainViewModel
import com.fdev.weatherdemo.presentation.component.CurrentWeatherSection
import com.fdev.weatherdemo.presentation.component.WeatherHourItem
import com.fdev.weatherdemo.presentation.theme.Background
import com.fdev.weatherdemo.presentation.theme.Blue
import com.fdev.weatherdemo.presentation.theme.DefaultHzMargin
import com.fdev.weatherdemo.presentation.theme.DefaultTopBarButtonMargin
import com.fdev.weatherdemo.presentation.theme.DefaultVtMargin

@Composable
fun WeatherDayDetailsScreen(viewModel: MainViewModel, onBackListener: () -> Unit) {
    val forecastDayDataState by viewModel.forecastDayDataState.collectAsState()
    val state = forecastDayDataState

    Box(
        modifier = Modifier
            .background(Background)
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        IconButton(modifier = Modifier.padding(horizontal = DefaultTopBarButtonMargin, vertical = DefaultTopBarButtonMargin), onClick = { onBackListener() }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_back),
                contentDescription = null,
                tint = Blue,
            )
        }

        if (state is WeatherDayDetailsState.DayDayDetails) {
            DayDetailsWeatherSection(state.weatherData)
        }
    }
}

@Composable
private fun DayDetailsWeatherSection(thisDay: ForecastData) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 72.dp)
    ) {
        CurrentWeatherSection(
            modifier = Modifier.weight(1f),
            thisDay.day,
        )

        WeatherHoursSection(thisDay.hour)
    }
}

@Composable
private fun WeatherHoursSection(days: List<Hour>) {
    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = DefaultVtMargin),
        contentPadding = PaddingValues(horizontal = DefaultHzMargin),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(days) { day ->
            WeatherHourItem(day)
        }
    }
}