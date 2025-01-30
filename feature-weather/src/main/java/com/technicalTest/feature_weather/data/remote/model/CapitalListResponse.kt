package com.technicalTest.feature_weather.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class CapitalListResponse(
    @SerializedName("results")
    val results: List<CapitalResponse>
)