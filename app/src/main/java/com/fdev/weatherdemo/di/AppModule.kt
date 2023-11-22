package com.fdev.weatherdemo.di

import android.content.Context
import android.content.SharedPreferences
import com.fdev.weatherdemo.BuildConfig
import com.fdev.weatherdemo.data.Repository
import com.fdev.weatherdemo.data.local.WeatherLocalDataSource
import com.fdev.weatherdemo.data.remote.IWeatherApi
import com.fdev.weatherdemo.data.remote.WeatherApi
import com.fdev.weatherdemo.domain.GetWeatherUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val SPREFS = "SPREFS"
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val newRequestBuilder: Request.Builder = chain.request().newBuilder()
                newRequestBuilder.addHeader("X-RapidAPI-Key", BuildConfig.RAPID_API_KEY)
                newRequestBuilder.addHeader("X-RapidAPI-Host", BuildConfig.RAPID_API_HOST)

                chain.proceed(newRequestBuilder.build())
            }
            .build()
    }

    @Singleton
    @Provides
    fun provideWeatherApi(okHttpClient: OkHttpClient): IWeatherApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.HOST_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(IWeatherApi::class.java)
    }

    @Singleton
    @Provides
    fun bindRepository(
        weatherApi: WeatherApi, localDataSource: WeatherLocalDataSource
    ): Repository {
        return Repository(weatherApi, localDataSource)
    }

    @Singleton
    @Provides
    fun bindGetWeatherUseCase(repository: Repository): GetWeatherUseCase {
        return GetWeatherUseCase(repository)
    }

    @Provides
    fun provideBasicSPrefs(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SPREFS, Context.MODE_PRIVATE)
    }

    @Singleton
    @Provides
    fun bindWeatherLocalDataSource(prefs: SharedPreferences): WeatherLocalDataSource {
        return WeatherLocalDataSource(prefs)
    }

}