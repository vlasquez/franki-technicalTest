package com.technicalTest.feature_weather.data.remote

import com.technicalTest.feature_weather.data.remote.model.CityWeatherResponse
import com.technicalTest.feature_weather.data.remote.model.ForecastWeatherResponse
import com.technicaltest.network.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

internal interface WeatherApi {

    companion object {
        private val UNITS = "metric"
        private val APP_ID = BuildConfig.WEATHER_API_KEY
    }

    @GET("/data/2.5/weather")
    suspend fun getWeatherByCity(
        @Query("q") query: String,
        @Query("appid") applicationId: String = APP_ID,
        @Query("units") units: String = UNITS
    ): Result<CityWeatherResponse>

    @GET("/data/2.5/forecast/")
    suspend fun getForecastWeatherByLocation(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("appid") applicationId: String = APP_ID,
    ): Result<ForecastWeatherResponse>
}