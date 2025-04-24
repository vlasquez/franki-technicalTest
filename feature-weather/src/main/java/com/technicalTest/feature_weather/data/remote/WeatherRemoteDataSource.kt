package com.technicalTest.feature_weather.data.remote

import com.technicalTest.feature_weather.data.WeatherDataSource
import com.technicalTest.feature_weather.data.remote.model.CityWeatherResponse
import com.technicalTest.feature_weather.data.remote.model.ForecastWeatherResponse
import javax.inject.Inject

internal class WeatherRemoteDataSource @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherDataSource {

    override suspend fun getWeatherByCity(
        city: String,
    ): Result<CityWeatherResponse> {
        return weatherApi.getWeatherByCity(getQuery(city))
    }

    override suspend fun getForecastWeatherByLocation(
        lat: Double,
        lon: Double
    ): Result<ForecastWeatherResponse> {
        return weatherApi.getForecastWeatherByLocation(lat, lon)
    }

    private fun getQuery(city: String): String =
        "$city"

    companion object {
        private const val COUNTRY_CODE = "US"
    }
}