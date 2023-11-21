package com.fdev.weatherdemo.domain

import com.fdev.weatherdemo.data.Repository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(private val repository: Repository)  {
    fun loadWeatherData(query: String?) = repository.loadWeatherData(query)
}