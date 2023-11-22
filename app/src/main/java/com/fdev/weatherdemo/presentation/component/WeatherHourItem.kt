@file:OptIn(ExperimentalGlideComposeApi::class)

package com.fdev.weatherdemo.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
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
import com.fdev.weatherdemo.data.entity.Hour
import com.fdev.weatherdemo.presentation.theme.DefaultHzMargin
import com.fdev.weatherdemo.presentation.theme.Shapes
import com.fdev.weatherdemo.presentation.theme.White
import com.fdev.weatherdemo.utils.DateHelper

@Composable
fun WeatherHourItem(forecastData: Hour) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(color = White, shape = Shapes.medium)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            modifier = Modifier,
            text = DateHelper.getFormattedDateForHours(forecastData.time),
            fontSize = 16.sp
        )

        GlideImage(
            modifier = Modifier.size(48.dp),
            model = forecastData.condition?.icon,
            contentDescription = null,
        )

        Text(
            modifier = Modifier,
            text = stringResource(R.string.temperature, forecastData.temp.toString()),
            fontSize = 18.sp
        )
    }
}