package com.fdev.weatherdemo.presentation

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fdev.weatherdemo.R
import com.fdev.weatherdemo.presentation.component.WeatherNextDaysItem
import com.fdev.weatherdemo.presentation.component.WeatherValueItem
import com.fdev.weatherdemo.presentation.theme.Background
import com.fdev.weatherdemo.presentation.theme.Blue
import com.fdev.weatherdemo.presentation.theme.DefaultHzMargin
import com.fdev.weatherdemo.presentation.theme.DefaultVtMargin
import com.fdev.weatherdemo.presentation.theme.Shapes
import com.fdev.weatherdemo.presentation.theme.White

//@Preview(showBackground = true)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherHomeScreen(viewModel: MainViewModel) {
    val currentWeatherState by viewModel.weatherDataState.collectAsState()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = Background,
        topBar = {
            TextField(
                value = viewModel.searchFieldValue,
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
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = Blue,
                        modifier = Modifier.clickable { viewModel.searchWeather() }
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp),
            )
        }) {
        Column {
            CurrentWeatherSection(
                Modifier
                    .weight(1f)
            )
            WeatherNextDaysSection()
        }
    }
}

@Composable
private fun CurrentWeatherSection(modifier: Modifier) {
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
            text = "25°C",
            fontSize = 88.sp,
            fontWeight = FontWeight.SemiBold,
        )

        Image(
            modifier = Modifier
                .fillMaxSize()
                .weight(1f),
            painter = painterResource(id = R.drawable.ic_cloudy_day),
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

            WeatherValueItem(modifierTemperature)
            WeatherValueItem(modifierTemperature)
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

            WeatherValueItem(modifierTemperature)
            WeatherValueItem(modifierTemperature)
            WeatherValueItem(modifierTemperature)
        }
    }
}

@Composable
private fun WeatherNextDaysSection() {
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
        WeatherNextDaysItem()
        WeatherNextDaysItem()
        WeatherNextDaysItem()
    }
}