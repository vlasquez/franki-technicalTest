package com.technicalTest.feature_weather.mapper

import com.technicalTest.feature_weather.data.remote.mapper.WeatherMapper
import com.technicalTest.feature_weather.data.remote.model.CityWeatherResponse
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WeatherMapperTest {

    private lateinit var weatherMapper: WeatherMapper

    @MockK
    private lateinit var weatherResponse: CityWeatherResponse


    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        weatherMapper = WeatherMapper()
    }

    @Test
    fun `GIVEN a WeatherResponse WHEN transformCityWeather THEN return CityWeather`() {
        // Given
        every { weatherResponse.name } returns "London"
        every { weatherResponse.coordinatesResponse.lat } returns 51.51
        every { weatherResponse.coordinatesResponse.lon } returns -0.13
        every { weatherResponse.cloudsResponse.cloudPercentage } returns 90
        every { weatherResponse.weatherResponse.first().description } returns "Cloudy"

        // When
        val result = weatherMapper.transformCityWeather(weatherResponse)

        // Then
        assertEquals(result.capitalName, weatherResponse.name)
        assertEquals(result.cityCoordinates.lat, weatherResponse.coordinatesResponse.lat, 0.0)
        assertEquals(result.cityCoordinates.lon, weatherResponse.coordinatesResponse.lon, 0.0)
        assertEquals(result.weather.cloudPercentage, weatherResponse.cloudsResponse.cloudPercentage)
        assertEquals(
            result.weather.description,
            weatherResponse.weatherResponse.first().description
        )

    }
}