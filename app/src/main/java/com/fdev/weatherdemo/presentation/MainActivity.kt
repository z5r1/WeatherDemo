package com.fdev.weatherdemo.presentation

import android.os.Bundle
import android.os.PersistableBundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import com.fdev.weatherdemo.presentation.theme.WeatherDemoViewTheme
import com.fdev.weatherdemo.presentation.theme.White
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: ComponentActivity()   {

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            WeatherDemoViewTheme {
//                val systemUiController = enableEdgeToEdge()
//                SideEffect {
//                    systemUiController.setStatusBarColor(color = Color.Transparent)
//                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    WeatherHomeScreen(mainViewModel)
                }
            }
        }
    }
}