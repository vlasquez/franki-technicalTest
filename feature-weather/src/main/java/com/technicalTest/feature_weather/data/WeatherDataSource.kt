package com.technicalTest.feature_weather.data

import com.technicalTest.feature_weather.data.remote.model.CityWeatherResponse

interface WeatherDataSource {
    suspend fun getWeatherByCity(city: String): Result<CityWeatherResponse>
}