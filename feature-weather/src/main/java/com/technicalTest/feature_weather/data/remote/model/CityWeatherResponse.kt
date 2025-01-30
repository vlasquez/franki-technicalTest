package com.technicalTest.feature_weather.data.remote.model

import com.google.gson.annotations.SerializedName

data class CityWeatherResponse(
    @SerializedName("clouds")
    val cloudsResponse: CloudsResponse,

    @SerializedName("coord")
    val coordinatesResponse: CoordinatesResponse,

    @SerializedName("dt")
    val requestTimestamp: Int,

    @SerializedName("id")
    val id: Int,

    @SerializedName("main")
    val mainTempResponse: MainTempResponse,

    @SerializedName("name")
    val name: String,

    @SerializedName("weather")
    val weatherResponse: List<WeatherResponse>,
)

data class CoordinatesResponse(
    val lat: Double,
    val lon: Double
)

data class CloudsResponse(
    @SerializedName("all")
    val cloudPercentage: Int
)

data class MainTempResponse(
    @SerializedName("feels_like")
    val feelsLike: Double,

    @SerializedName("humidity")
    val humidity: Int,

    @SerializedName("pressure")
    val pressure: Int,

    @SerializedName("temp")
    val temp: Double,

    @SerializedName("temp_max")
    val maxTemp: Double,

    @SerializedName("temp_min")
    val minTemp: Double
)

data class WeatherResponse(
    val description: String,
    val id: Int,
    val main: String,
    val icon: String
)