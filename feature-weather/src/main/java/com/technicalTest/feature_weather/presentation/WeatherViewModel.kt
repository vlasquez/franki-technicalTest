package com.technicalTest.feature_weather.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technicalTest.feature_weather.domain.model.CityWeather
import com.technicalTest.feature_weather.domain.usecase.GetWeatherInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class WeatherViewModel @Inject constructor(
    private val getWeatherInfo: GetWeatherInfo
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
                }.onFailure {
                    _viewState.value = ViewState.WeatherError(it.message)
                }
            } else {
                _viewState.value = ViewState.WeatherError("Location is empty")
            }
        }
    }


    sealed class ViewState {
        data object Loading : ViewState()
        data object Refreshing : ViewState()
        data class WeatherLoaded(val cityWeather: CityWeather) : ViewState()
        data class WeatherError(val message: String?) : ViewState()
    }
}