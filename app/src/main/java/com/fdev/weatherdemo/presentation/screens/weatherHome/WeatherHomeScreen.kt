@file:OptIn(
    ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class
)

package com.fdev.weatherdemo.presentation.screens.weatherHome

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fdev.weatherdemo.R
import com.fdev.weatherdemo.data.entity.ForecastData
import com.fdev.weatherdemo.data.entity.WeatherData
import com.fdev.weatherdemo.presentation.MainViewModel
import com.fdev.weatherdemo.presentation.component.CurrentWeatherSection
import com.fdev.weatherdemo.presentation.component.LoadingView
import com.fdev.weatherdemo.presentation.component.WeatherNextDaysItem
import com.fdev.weatherdemo.presentation.theme.Background
import com.fdev.weatherdemo.presentation.theme.Blue
import com.fdev.weatherdemo.presentation.theme.DefaultHzMargin
import com.fdev.weatherdemo.presentation.theme.DefaultTopBarButtonMargin
import com.fdev.weatherdemo.presentation.theme.DefaultVtMargin
import com.fdev.weatherdemo.presentation.theme.Shapes
import com.fdev.weatherdemo.presentation.theme.White

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WeatherHomeScreen(viewModel: MainViewModel, listener: OnWeatherSelectListener) {
    val currentWeatherState by viewModel.weatherDataState.collectAsState()
    val pullRefreshState = rememberPullRefreshState(
        false,
        { viewModel.searchWeather() }
    )
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = Background,
        topBar = {
            TopBar(viewModel)
        }) {

        PullRefresh(pullRefreshState)

        when (val state = currentWeatherState) {
            is WeatherHomeState.Loading -> {
                LoadingView()
            }

            is WeatherHomeState.SuccessWeatherHome -> {
                HomeWeatherSectionView(state.weatherData, listener)
            }

            is WeatherHomeState.Error -> {
                ErrorView(state.errorMessageId)
            }
        }
    }
}

@Composable
private fun TopBar(viewModel: MainViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current
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
            IconButton(onClick = {
                viewModel.searchWeather()
                keyboardController?.hide()
            }) {
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
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = {
            keyboardController?.hide()
        })
    )
}

@Composable
private fun PullRefresh(pullRefreshState: PullRefreshState) {
    Box(
        Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
            .verticalScroll(rememberScrollState())
    ) {
        PullRefreshIndicator(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 20.dp),
            refreshing = false,
            state = pullRefreshState
        )
    }
}

@Composable
private fun ErrorView(idString: Int) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .wrapContentSize()
                .padding(top = 70.dp)
                .padding(horizontal = DefaultHzMargin)
                .align(Alignment.TopCenter),
            textAlign = TextAlign.Center,
            text = stringResource(id = idString)
        )
    }
}

@Composable
private fun HomeWeatherSectionView(
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
        if (LocalConfiguration.current.orientation != Configuration.ORIENTATION_LANDSCAPE) {
            Text(
                modifier = Modifier
                    .wrapContentSize()
                    .align(Alignment.CenterHorizontally),
                text = data.locationData.name,
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }

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