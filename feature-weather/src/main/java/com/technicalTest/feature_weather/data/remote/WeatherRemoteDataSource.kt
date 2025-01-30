package com.technicalTest.feature_weather.data.remote

import com.technicalTest.feature_weather.data.WeatherDataSource
import com.technicalTest.feature_weather.data.remote.model.CityWeatherResponse
import javax.inject.Inject

internal class WeatherRemoteDataSource @Inject constructor(
    private val weatherApi: WeatherApi
) : WeatherDataSource {

    override suspend fun getWeatherByCity(
        city: String,
        state: String
    ): Result<CityWeatherResponse> {
        return weatherApi.getWeatherByCity(getQuery(city, state))
    }

    private fun getQuery(city: String, state: String): String =
        "$city,$state,$COUNTRY_CODE"

    companion object {
        private const val COUNTRY_CODE = "US"
    }
}