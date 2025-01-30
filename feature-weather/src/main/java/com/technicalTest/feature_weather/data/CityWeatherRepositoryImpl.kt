package com.technicalTest.feature_weather.data

import com.technicalTest.feature_weather.data.remote.WeatherRemoteDataSource
import com.technicalTest.feature_weather.data.remote.mapper.WeatherMapper
import com.technicalTest.feature_weather.data_access.CityWeatherRepository
import com.technicalTest.feature_weather.domain.model.CityWeather
import javax.inject.Inject

internal class CityWeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherMapper: WeatherMapper
) : CityWeatherRepository {

    override suspend fun getWeatherByLocation(location: String): Result<CityWeather> =
        weatherRemoteDataSource.getWeatherByCity(location).map {
            weatherMapper.transformCityWeather(it)
        }

}