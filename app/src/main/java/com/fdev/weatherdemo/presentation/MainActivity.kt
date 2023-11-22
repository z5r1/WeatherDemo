package com.fdev.weatherdemo.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.fdev.weatherdemo.presentation.navigation.NavGraph
import com.fdev.weatherdemo.presentation.theme.Background
import com.fdev.weatherdemo.presentation.theme.WeatherDemoViewTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity: ComponentActivity()   {

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.searchWeather()
            }
        }

        setContent {
            WeatherDemoViewTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    contentColor = Background,
                    color = Background
                ) {
                    NavGraph(mainViewModel)
                }
            }
        }
    }
}