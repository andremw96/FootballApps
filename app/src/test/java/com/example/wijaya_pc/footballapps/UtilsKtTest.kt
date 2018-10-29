package com.example.wijaya_pc.footballapps

import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat

class UtilsKtTest {

    @Test
    fun testDateToSimpleString() {
        val date = SimpleDateFormat("MM/dd/yyyy").parse("10/25/2018")
        assertEquals("Thu, 25 Oct 2018", dateToSimpleString(date))
    }
}