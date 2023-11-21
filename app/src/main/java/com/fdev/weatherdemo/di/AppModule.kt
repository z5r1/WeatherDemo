package com.fdev.weatherdemo.di

import com.fdev.weatherdemo.BuildConfig
import com.fdev.weatherdemo.data.Repository
import com.fdev.weatherdemo.data.remote.IWeatherApi
import com.fdev.weatherdemo.domain.GetWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideWeatherApi() : IWeatherApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IWeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun bindRepository() : Repository {
        return Repository()
    }
    @Singleton
    @Provides
    fun bindGetWeatherUseCase(repository: Repository) : GetWeatherUseCase {
        return GetWeatherUseCase(repository)
    }
}