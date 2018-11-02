package com.example.wijaya_pc.footballapps

import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat

class UtilsKtTest {

    @Test
    fun testDateToSimpleString() {
        val date = SimpleDateFormat("MM/dd/yyyy").parse("10/25/2018")
        assertEquals("Thu, 25 Oct 2018", dateToSimpleString(date))
    }

    @Test
    fun testDateTimeToSimpleString() {
        val dateTime = SimpleDateFormat("MM/dd/yyyy HH:mm").parse("11/03/2018 17:30:00+00:00")
        assertEquals("Sat, 03 Nov 2018 17:30", dateTimeToSimpleString(dateTime))
    }

    @Test
    fun testToGMTFormat() {
        val date = dateToSimpleString(SimpleDateFormat("MM/dd/yyyy").parse("11/03/2018"))
        val time = "17:30:00+00:00"
        assertEquals("Sun, 04 Nov 2018 00:30", dateTimeToSimpleString(toGMTFormat(date, time)))
    }

    @Test
    fun testDateToSimpleStringforCalendar() {
        val date = SimpleDateFormat("EEE, dd MMM yyyy HH:mm").parse("Sun, 04 Nov 2018 00:30")
        assertEquals("2018/11/04", dateToSimpleStringforCalendar(date))
    }

    @Test
    fun testDateTimeToSimpleStringforCalendar() {
        val dateTime = SimpleDateFormat("EEE, dd MMM yyyy HH:mm").parse("Sun, 04 Nov 2018 00:30")
        assertEquals("2018/11/04 00:30", dateTimeToSimpleStringforCalendar(dateTime))
    }

    @Test
    fun testToGMTFormatforCalendar() {
        val date = dateToSimpleStringforCalendar(SimpleDateFormat("EEE, dd MMM yyyy").parse("Sat, 03 Nov 2018"))
        val time = "17:30:00+00:00"
        assertEquals("2018/11/04 00:30", dateTimeToSimpleStringforCalendar(toGMTFormatforCalendar(date, time)))
    }


}