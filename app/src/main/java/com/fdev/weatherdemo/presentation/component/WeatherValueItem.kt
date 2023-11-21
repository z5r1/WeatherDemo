package com.fdev.weatherdemo.presentation.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WeatherValueItem(modifier: Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(bottom = 2.dp),
            text = "Min",
            fontSize = 14.sp
        )

        Text(
            modifier = Modifier
                .wrapContentSize(),
            text = "25°",
            fontSize = 24.sp
        )
    }
}