package com.technicalTest.feature_weather.data

import com.technicalTest.feature_weather.data.remote.model.CityWeatherResponse
import com.technicalTest.feature_weather.data.remote.model.ForecastWeatherResponse

interface WeatherDataSource {
    suspend fun getWeatherByCity(city: String): Result<CityWeatherResponse>
    suspend fun getForecastWeatherByLocation(
        lat: Double,
        lon: Double
    ): Result<ForecastWeatherResponse>
}