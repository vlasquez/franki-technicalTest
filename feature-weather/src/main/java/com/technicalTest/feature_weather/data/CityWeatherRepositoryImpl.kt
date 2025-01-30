package com.technicalTest.feature_weather.data

import com.technicalTest.feature_weather.data.remote.WeatherRemoteDataSource
import com.technicalTest.feature_weather.data.remote.mapper.WeatherMapper
import com.technicalTest.feature_weather.data_access.CityWeatherRepository
import com.technicalTest.feature_weather.domain.model.CityWeather
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import javax.inject.Inject

internal class CityWeatherRepositoryImpl @Inject constructor(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherMapper: WeatherMapper
) : CityWeatherRepository {

    override suspend fun getCitiesWeather(): List<CityWeather> {
        val capitals = CoroutineScope(Dispatchers.IO).async {
            capitalsRemoteDataSource.getCapitalCities()
        }

        val cityWeatherList = mutableListOf<CityWeather>()

        capitals.await().onSuccess { capitalList ->
            capitalList.results.forEach { capital ->

                weatherRemoteDataSource.getWeatherByCity(
                    capital.getFixedCapitalName(),
                    capital.getStateCode()
                ).onSuccess { cityWeather ->
                    cityWeatherList.add(weatherMapper.transformCityWeather(capital, cityWeather))
                }.onFailure {
                    throw it
                }
            }
        }.onFailure {
            throw it
        }

        return cityWeatherList
    }
}