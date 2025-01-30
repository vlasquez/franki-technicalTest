package com.technicalTest.feature_weather.data.source.remote.model

import com.google.gson.annotations.SerializedName

data class CapitalResponse(
    @SerializedName("objectId")
    val objectId: String,
    @SerializedName("StateName")
    val stateName: String,
    @SerializedName("capital")
    val capitalName: String,
    @SerializedName("iso3166")
    val iso3166: String,
) {
    /** Workaround, some capitals contains special characters **/
    fun getFixedCapitalName(): String =
        if (capitalName.contains("["))
            capitalName.substring(0, capitalName.length - 3)
        else
            capitalName

    fun getStateCode(): String = iso3166.split("-").last()
}
