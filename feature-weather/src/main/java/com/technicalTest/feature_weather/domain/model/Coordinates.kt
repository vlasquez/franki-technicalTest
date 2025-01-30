package com.technicalTest.feature_weather.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Coordinates(
    val lat: Double,
    val lon: Double
) : Parcelable