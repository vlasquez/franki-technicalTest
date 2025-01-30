package com.technicalTest.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
internal class DateExtKtTest {


    @Test
    fun `GIVEN a valid millisecond WHEN fromMillisToMinutes is called THEN return a valid HHmmss String`() {
        val millis = 208643L
        val result = fromMillisToMinutes(millis)
        assertEquals("03:28", result)
    }

    @Test
    fun `GIVEN 0 WHEN fromMillisToMinutes is called THEN return an empty String`() {
        val millis = 0L
        val result = fromMillisToMinutes(millis)
        assertEquals("", result)
    }

    @Test
    fun `GIVEN a valid date WHEN formatDate THEN return a date in yyyyMMdd`() {
        val date = "2005-01-29T12:00:00Z"
        val result = formatDate(date)
        assertEquals("January 29, 2005", result)
    }
}
