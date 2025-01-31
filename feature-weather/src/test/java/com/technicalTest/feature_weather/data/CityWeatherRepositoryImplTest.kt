package com.technicalTest.feature_weather.data

import com.technicalTest.feature_weather.data.remote.WeatherRemoteDataSource
import com.technicalTest.feature_weather.data.remote.mapper.WeatherMapper
import com.technicalTest.feature_weather.data.remote.model.CityWeatherResponse
import com.technicalTest.feature_weather.data_access.CityWeatherRepository
import com.technicalTest.feature_weather.domain.model.CityWeather
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CityWeatherRepositoryImplTest {

    private lateinit var cityWeatherRepositoryImpl: CityWeatherRepository

    @MockK
    private lateinit var weatherRemoteDataSource: WeatherRemoteDataSource

    @MockK
    private lateinit var weatherMapper: WeatherMapper

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)
        cityWeatherRepositoryImpl = CityWeatherRepositoryImpl(
            weatherRemoteDataSource = weatherRemoteDataSource,
            weatherMapper = weatherMapper
        )
    }

    @Test
    fun `GIVEN a location WHEN getWeatherByLocation THEN return a response with a city weather`() {
        runTest  {
            // Given
            val location = "London"
            val cityWeatherResponse = mockk<CityWeatherResponse>(relaxed = true) {
                every { name } returns "London"
                every { coordinatesResponse.lat } returns 51.51
                every { coordinatesResponse.lon } returns -0.13
            }
            val cityWeather = mockk<CityWeather>(relaxed = true) {
                every { capitalName } returns "London"
                every { cityCoordinates.lat } returns 51.51
                every { cityCoordinates.lon } returns -0.13
            }
            coEvery { weatherRemoteDataSource.getWeatherByCity(location) } returns Result.success(
                cityWeatherResponse
            )
            coEvery { weatherMapper.transformCityWeather(cityWeatherResponse) } returns cityWeather

            // When
            val result = cityWeatherRepositoryImpl.getWeatherByLocation(location)

            // Then
            assert(result.isSuccess)
            assert(cityWeather == result.getOrNull())
            assert(cityWeather.capitalName == result.getOrNull()?.capitalName)
        }
    }

    @Test
    fun `GIVEN a location WHEN getWeatherByLocation THEN return a failure`() {
        runTest  {
            // Given
            val location = "London"
            coEvery { weatherRemoteDataSource.getWeatherByCity(location) } returns Result.failure(
                Throwable()
            )

            // When
            val result = cityWeatherRepositoryImpl.getWeatherByLocation(location)

            // Then
            assert(result.isFailure)
        }
    }
}
