@file:OptIn(ExperimentalGlideComposeApi::class)

package com.fdev.weatherdemo.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.fdev.weatherdemo.R
import com.fdev.weatherdemo.data.entity.CurrentForecastData
import com.fdev.weatherdemo.data.entity.Day
import com.fdev.weatherdemo.presentation.theme.DefaultHzMargin
import com.fdev.weatherdemo.presentation.theme.DefaultVtMargin
import com.fdev.weatherdemo.presentation.theme.Shapes
import com.fdev.weatherdemo.presentation.theme.White

@Composable
fun CurrentWeatherSection(
    modifier: Modifier,
    thisDay: Day,
    currentForecastData: CurrentForecastData? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize(),
            text = stringResource(
                R.string.temperature,
                currentForecastData?.temp?.toString() ?: thisDay.avgTemp.toString()
            ),
            fontSize = 50.sp,
            fontWeight = FontWeight.SemiBold,
        )

        GlideImage(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            model = currentForecastData?.condition?.icon ?: thisDay.condition?.icon,
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

            WeatherValueItem(modifierTemperature, thisDay.avghumidity, R.string.avghumidity)
            WeatherValueItem(modifierTemperature, thisDay.maxwindKph, R.string.wind_speed)
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
                thisDay.minTemp,
                R.string.min_temperature
            )
            WeatherValueItem(
                modifierTemperature,
                thisDay.avgTemp,
                R.string.avg_temperature
            )
            WeatherValueItem(
                modifierTemperature,
                thisDay.maxTemp,
                R.string.max_temperature
            )
        }
    }
}
