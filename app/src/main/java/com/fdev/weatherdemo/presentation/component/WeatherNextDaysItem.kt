package com.fdev.weatherdemo.presentation.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fdev.weatherdemo.R
import com.fdev.weatherdemo.presentation.theme.DefaultHzMargin

@Preview(showBackground = true)
@Composable
fun WeatherNextDaysItem() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {  }
            .padding(horizontal = DefaultHzMargin)
    ) {
        Image(
            modifier = Modifier.size(64.dp),
            painter = painterResource(id = R.drawable.ic_cloudy_day),
            contentDescription = null,
        )

        Text(
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 10.dp),
            text = "25°",
            fontSize = 20.sp
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .align(alignment = Alignment.CenterVertically)
                .padding(start = 16.dp),
            text = "Saratov",
            fontSize = 20.sp
        )
    }
}