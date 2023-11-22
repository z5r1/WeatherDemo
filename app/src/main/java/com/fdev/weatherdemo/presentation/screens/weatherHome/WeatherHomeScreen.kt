@file:OptIn(ExperimentalGlideComposeApi::class, ExperimentalMaterial3Api::class)

package com.fdev.weatherdemo.presentation.screens.weatherHome

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.fdev.weatherdemo.R
import com.fdev.weatherdemo.data.entity.ForecastData
import com.fdev.weatherdemo.data.entity.WeatherData
import com.fdev.weatherdemo.presentation.MainViewModel
import com.fdev.weatherdemo.presentation.component.CurrentWeatherSection
import com.fdev.weatherdemo.presentation.component.LoadingItem
import com.fdev.weatherdemo.presentation.component.WeatherNextDaysItem
import com.fdev.weatherdemo.presentation.theme.Background
import com.fdev.weatherdemo.presentation.theme.Blue
import com.fdev.weatherdemo.presentation.theme.DefaultHzMargin
import com.fdev.weatherdemo.presentation.theme.DefaultTopBarButtonMargin
import com.fdev.weatherdemo.presentation.theme.DefaultVtMargin
import com.fdev.weatherdemo.presentation.theme.Shapes
import com.fdev.weatherdemo.presentation.theme.White

//@Preview(showBackground = true)
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherHomeScreen(viewModel: MainViewModel, listener: OnWeatherSelectListener) {
    val currentWeatherState by viewModel.weatherDataState.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = Background,
        topBar = {
            TextField(
                value = viewModel.searchFieldValue.collectAsState().value,
                onValueChange = {
                    viewModel.updateSearchField(it)
                },
                textStyle = TextStyle.Default.copy(fontSize = 20.sp),
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Blue,
                    disabledTextColor = Color.Transparent,
                    backgroundColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_location),
                        contentDescription = null,
                        tint = Blue
                    )
                },
                trailingIcon = {
                    IconButton(onClick = { viewModel.searchWeather() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = null,
                            tint = Blue,
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = DefaultTopBarButtonMargin),
            )
        }) {
        when (val state = currentWeatherState) {
            is WeatherHomeState.Loading -> {
                LoadingItem()
            }

            is WeatherHomeState.SuccessWeatherHome -> {
                HomeWeatherSection(state.weatherData, listener)
            }

            is WeatherHomeState.Error -> {
                Error()
            }
        }
    }
}

@Composable
private fun Error() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(top =  70.dp)
                .padding(horizontal =  DefaultHzMargin)
                .align(Alignment.TopCenter),
            textAlign = TextAlign.Center,
            text = stringResource(id = R.string.error_loading)
        )
    }
}

@Composable
private fun HomeWeatherSection(
    data: WeatherData,
    listener: OnWeatherSelectListener
) {
    val thisDay = data.nowForecast.day

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 72.dp)
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.CenterHorizontally),
            text = data.locationData.name,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
        )

        CurrentWeatherSection(
            modifier = Modifier.weight(1f),
            thisDay,
            data.currentWeather
        )
        WeatherNextDaysSection(data.forecast.days, listener)
    }
}


@Composable
private fun WeatherNextDaysSection(days: List<ForecastData>, listener: OnWeatherSelectListener) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = DefaultVtMargin, horizontal = DefaultHzMargin)
            .background(color = White, shape = Shapes.large)
            .padding(vertical = DefaultVtMargin),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        days.forEach {
            WeatherNextDaysItem(it, listener)
        }
    }
}