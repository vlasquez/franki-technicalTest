package com.technicalTest.feature_weather.data_access

import com.technicalTest.feature_weather.domain.model.CityWeather

internal interface CityWeatherRepository {

    suspend fun getWeatherByLocation(location: String): Result<CityWeather>
    suspend fun getForecastWeatherByLocation(lat: Double, lon: Double): Result<List<CityWeather>>
}