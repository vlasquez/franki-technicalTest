package com.technicalTest.feature_weather.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val description: String,
    val weather: String,
    val cloudPercentage: Int,
    val temperature: Temperature,
    val icon: String
) : Parcelable