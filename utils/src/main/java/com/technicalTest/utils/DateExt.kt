package com.technicalTest.utils


import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

fun fromMillisToMinutes(millis: Long): String {
    return if (millis > 0) {
        String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(millis) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(millis) % TimeUnit.MINUTES.toSeconds(1)
        )
    } else ""

}

fun formatDate(date: String): String {
    val originalFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
    val targetFormat: DateFormat = SimpleDateFormat("MMMM dd, YYYY", Locale.getDefault())
    val newDate: Date = originalFormat.parse(date)!!
    return targetFormat.format(newDate)
}