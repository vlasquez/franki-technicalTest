package com.technicalTest.feature_weather.presentation

import app.cash.turbine.test
import com.technicalTest.feature_weather.domain.model.CityWeather
import com.technicalTest.feature_weather.domain.usecase.GetWeatherInfo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

class WeatherViewModelTest {

    private lateinit var viewModel: WeatherViewModel

    @MockK
    private lateinit var getWeatherInfo: GetWeatherInfo

    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxed = true)

        viewModel = WeatherViewModel(
            getWeatherInfo = getWeatherInfo

        )
        Dispatchers.setMain(testDispatcher)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `GIVEN a location WHEN getWeatherByLocation THEN return a response with a city weather`() {
        runTest {

            try {
                // Given
                val location = "London"
                val cityWeather = mockk<CityWeather>(relaxed = true) {
                    every { capitalName } returns "London"
                    every { cityCoordinates.lat } returns 51.51
                    every { cityCoordinates.lon } returns -0.13
                }
                coEvery { getWeatherInfo(location) } returns Result.success(cityWeather)

                // When
                viewModel.getWeatherByLocation(location)
                advanceUntilIdle()

                viewModel.viewState.test {
                    // Then
                    assert(WeatherViewModel.ViewState.WeatherLoaded(cityWeather) == awaitItem())
                    cancelAndIgnoreRemainingEvents()
                }
            } finally {
                Dispatchers.resetMain()
            }

        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `GIVEN a location WHEN getWeatherByLocation THEN return a failure`() {
        runTest {
            // Given
            val location = "London"
            coEvery { getWeatherInfo(location) } returns Result.failure(Throwable("Unknown error"))

            // When
            viewModel.getWeatherByLocation(location)
            advanceUntilIdle()

            viewModel.viewState.test {
                // Then
                assert(WeatherViewModel.ViewState.WeatherError("Unknown error") == awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `GIVEN an empty location WHEN getWeatherByLocation THEN return a failure with the specific message`() {
        runTest {
            // Given
            val location = ""
            // When
            viewModel.getWeatherByLocation(location)
            advanceUntilIdle()

            viewModel.viewState.test {
                // Then
                assert(WeatherViewModel.ViewState.WeatherError("Location is empty") == awaitItem())
                cancelAndIgnoreRemainingEvents()
            }
        }
    }
}