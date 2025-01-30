package com.technicalTest.feature_weather.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Temperature(
    val feelsLike: Double,
    val humidity: String,
    val pressure: Int,
    val temp: String,
    val maxTemp: String,
    val minTemp: String
) : Parcelable