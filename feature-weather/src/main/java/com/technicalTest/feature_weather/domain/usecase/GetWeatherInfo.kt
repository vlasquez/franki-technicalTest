package com.technicalTest.feature_weather.domain.usecase

import com.technicalTest.feature_weather.data_access.CityWeatherRepository
import com.technicalTest.feature_weather.domain.model.CityWeather
import javax.inject.Inject

internal class GetWeatherInfo @Inject constructor(private val weatherRepository: CityWeatherRepository) {

    suspend operator fun invoke(): List<CityWeather> =
        weatherRepository.getCitiesWeather()
}