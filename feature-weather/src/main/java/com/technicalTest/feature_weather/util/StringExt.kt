package com.technicalTest.feature_weather.util

import kotlin.math.roundToInt

fun Double.toDegrees(): String =
    this.roundToInt().toString().plus("\u00B0")
