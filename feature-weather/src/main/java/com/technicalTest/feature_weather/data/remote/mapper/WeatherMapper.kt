package com.technicalTest.feature_weather.data.remote.mapper

import com.technicalTest.feature_weather.data.remote.model.CityWeatherResponse
import com.technicalTest.feature_weather.data.remote.model.CoordinatesResponse
import com.technicalTest.feature_weather.data.remote.model.MainTempResponse
import com.technicalTest.feature_weather.domain.model.CityWeather
import com.technicalTest.feature_weather.domain.model.Coordinates
import com.technicalTest.feature_weather.domain.model.Temperature
import com.technicalTest.feature_weather.domain.model.Weather
import com.technicalTest.feature_weather.util.toDegrees
import javax.inject.Inject

class WeatherMapper @Inject constructor() {
    fun transformCityWeather(
        weatherResponse: CityWeatherResponse
    ): CityWeather {

        return CityWeather(
            capitalName = weatherResponse.name,
            requestTimestamp = weatherResponse.requestTimestamp,
            cityCoordinates = transformCoordinates(weatherResponse.coordinatesResponse),
            weather = transformWeather(weatherResponse)
        )
    }

    private fun transformCoordinates(coordinatesResponse: CoordinatesResponse): Coordinates {
        return with(coordinatesResponse) {
            Coordinates(lat = this.lat, lon = this.lon)
        }
    }

    private fun transformWeather(weatherResponse: CityWeatherResponse): Weather {
        return with(weatherResponse) {
            Weather(
                cloudPercentage = this.cloudsResponse.cloudPercentage,
                description = this.weatherResponse.first().description,
                weather = this.weatherResponse.first().main,
                icon = this.weatherResponse.first().icon,
                temperature = transformTemperature(weatherResponse.mainTempResponse)
            )
        }
    }

    private fun transformTemperature(mainTempResponse: MainTempResponse): Temperature {
        return with(mainTempResponse) {
            Temperature(
                feelsLike = this.feelsLike,
                humidity = this.humidity.toString(),
                pressure = this.pressure,
                temp = this.temp.toDegrees(),
                maxTemp = this.maxTemp.toDegrees(),
                minTemp = this.minTemp.toDegrees(),
            )
        }
    }
}