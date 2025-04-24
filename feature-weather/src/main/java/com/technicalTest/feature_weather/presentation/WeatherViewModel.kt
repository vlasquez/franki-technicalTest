package com.technicalTest.feature_weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technicalTest.feature_weather.domain.model.CityWeather
import com.technicalTest.feature_weather.domain.model.Coordinates
import com.technicalTest.feature_weather.domain.usecase.GetForecastWeatherByLocation
import com.technicalTest.feature_weather.domain.usecase.GetWeatherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WeatherViewModel @Inject constructor(
    private val getWeatherInfo: GetWeatherInfo,
    private val getCityForecast: GetForecastWeatherByLocation
) : ViewModel() {
    private val _viewState = MutableStateFlow<ViewState>(ViewState.Loading)
    val viewState: StateFlow<ViewState> get() = _viewState

    private var currentLocation: String? = null

    fun getWeatherByLocation(location: String) {
        viewModelScope.launch {
            if (location.isNotEmpty()) {
                currentLocation = location
                getWeatherInfo(location).onSuccess {
                    _viewState.value = ViewState.WeatherLoaded(it)
                    getForecastWeather(coordinates = it.cityCoordinates)
                }.onFailure {
                    _viewState.value = ViewState.WeatherError(it.message)
                }
            } else {
                _viewState.value = ViewState.WeatherError("Location is empty")
            }
        }
    }

    fun getForecastWeather(coordinates: Coordinates) {
        viewModelScope.launch {
            getCityForecast(
                lat = coordinates.lat,
                lon = coordinates.lon
            ).onSuccess {
                _viewState.update {
                    if (_viewState.value is ViewState.WeatherLoaded) {
                        (it as ViewState.WeatherLoaded).copy(
                            cityWeatherForecast = it.cityWeatherForecast
                        )
                    } else {
                        _viewState.value
                    }
                }
            }.onFailure {
                _viewState.value = ViewState.WeatherError(it.message)
            }
        }
    }

    sealed class ViewState {
        data object Loading : ViewState()
        data object Refreshing : ViewState()
        data class WeatherLoaded(
            val cityWeather: CityWeather,
            val cityWeatherForecast: List<CityWeather>? = null
        ) : ViewState()

        data class WeatherError(val message: String?) : ViewState()
    }
}