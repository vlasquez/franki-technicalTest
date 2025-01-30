package com.technicalTest.feature_weather.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CityWeather(
    val capitalName: String,
    val cityCoordinates: Coordinates,
    val weather: Weather,
    val requestTimestamp: Int,
) : Parcelable