package com.fdev.weatherdemo.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.fdev.weatherdemo.R
import com.fdev.weatherdemo.data.entity.ForecastData
import com.fdev.weatherdemo.data.entity.WeatherData
import com.fdev.weatherdemo.presentation.component.WeatherNextDaysItem
import com.fdev.weatherdemo.presentation.component.WeatherValueItem
import com.fdev.weatherdemo.presentation.theme.Background
import com.fdev.weatherdemo.presentation.theme.Blue
import com.fdev.weatherdemo.presentation.theme.DefaultHzMargin
import com.fdev.weatherdemo.presentation.theme.DefaultVtMargin
import com.fdev.weatherdemo.presentation.theme.LightBackground
import com.fdev.weatherdemo.presentation.theme.Shapes
import com.fdev.weatherdemo.presentation.theme.White
import com.fdev.weatherdemo.presentation.weatherMain.WeatherDataState

//@Preview(showBackground = true)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherHomeScreen(viewModel: MainViewModel) {
    val currentWeatherState by viewModel.weatherDataState.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding().background(brush = Brush.verticalGradient(
                colors = listOf(
                    LightBackground,
                    Background
                )
            )),
        containerColor = Color.Transparent,
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
                    IconButton(onClick = { viewModel.searchWeather()  }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_search),
                            contentDescription = null,
                            tint = Blue,
                        )
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
            )
        }) {
        Column {
            when (currentWeatherState) {
                is WeatherDataState.Loading -> {

                }
                is WeatherDataState.Success -> {
                    val data = (currentWeatherState as WeatherDataState.Success).weatherData
                    CurrentWeatherSection(
                        Modifier
                            .weight(1f),
                        data
                    )
                    WeatherNextDaysSection(data.forecast.days)
                }
                is WeatherDataState.Error -> {

                }
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
private fun CurrentWeatherSection(modifier: Modifier, weatherData: WeatherData) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(top = 72.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize(),
            text = weatherData.locationData.name,
            fontSize = 30.sp,
            fontWeight = FontWeight.SemiBold,
        )

        Text(
            modifier = Modifier
                .wrapContentSize(),
            text = stringResource(R.string.temperature, weatherData.currentData.temp.toString()),
            fontSize = 50.sp,
            fontWeight = FontWeight.SemiBold,
        )

        GlideImage(modifier = Modifier
            .fillMaxSize()
            .weight(1f),
            model = weatherData.currentData.condition?.icon,
            contentDescription = null,
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DefaultHzMargin)
                .padding(bottom = DefaultVtMargin),
        ) {
            val modifierTemperature = Modifier
                .weight(1f)
                .wrapContentSize()

            WeatherValueItem(modifierTemperature, weatherData.nowForecast.day.avghumidity, R.string.avghumidity)
            WeatherValueItem(modifierTemperature, weatherData.nowForecast.day.maxwindKph, R.string.wind_speed)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = DefaultHzMargin)
                .background(color = White, shape = Shapes.large)
                .padding(vertical = DefaultVtMargin, horizontal = DefaultHzMargin),
        ) {
            val modifierTemperature = Modifier
                .weight(1f)
                .wrapContentSize()

            WeatherValueItem(
                modifierTemperature,
                weatherData.nowForecast.day.minTemp,
                R.string.min_temperature
            )
            WeatherValueItem(
                modifierTemperature,
                weatherData.nowForecast.day.avgTemp,
                R.string.avg_temperature
            )
            WeatherValueItem(
                modifierTemperature,
                weatherData.nowForecast.day.maxTemp,
                R.string.max_temperature
            )
        }
    }
}

@Composable
private fun WeatherNextDaysSection(days: List<ForecastData>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = DefaultVtMargin, horizontal = DefaultHzMargin)
            .background(color = White, shape = Shapes.large)
            .padding(vertical = DefaultVtMargin),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .align(alignment = Alignment.CenterHorizontally)
                .padding(bottom = 10.dp),
            text = "Next days",
        )
        days.forEach {
            WeatherNextDaysItem(it)
        }
    }
}