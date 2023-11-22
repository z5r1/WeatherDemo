@file:OptIn(ExperimentalGlideComposeApi::class)

package com.fdev.weatherdemo.presentation.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.fdev.weatherdemo.R
import com.fdev.weatherdemo.data.entity.ForecastData
import com.fdev.weatherdemo.presentation.screens.weatherHome.OnWeatherSelectListener
import com.fdev.weatherdemo.presentation.theme.DefaultHzMargin
import com.fdev.weatherdemo.utils.DateHelper

@Composable
fun WeatherNextDaysItem(forecastData: ForecastData, listener: OnWeatherSelectListener) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { listener.onSelectDay(forecastData) }
            .padding(horizontal = DefaultHzMargin)
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 16.dp),
            text = DateHelper.getFormattedDateForDays(forecastData.date),
            fontSize = 16.sp
        )

        Text(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(horizontal = 10.dp),
            text = stringResource(R.string.temperature, forecastData.day.avgTemp.toString()),
            fontSize = 18.sp
        )

        GlideImage(
            modifier = Modifier.size(48.dp),
            model = forecastData.day.condition?.icon,
            contentDescription = null,
        )
    }
}