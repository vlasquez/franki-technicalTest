package com.technicalTest.feature_weather.data_access

import com.technicalTest.feature_weather.domain.model.CityWeather

internal interface CityWeatherRepository {

    suspend fun getCitiesWeather(): List<CityWeather>
}