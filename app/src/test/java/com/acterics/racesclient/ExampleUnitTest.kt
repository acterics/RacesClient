package com.acterics.racesclient

import com.acterics.racesclient.utils.Formats
import com.acterics.racesclient.utils.suffixedFormattedDate
import org.joda.time.DateTime
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun formatTest() {
        val dateTime = DateTime.now().withDayOfMonth(12)

        println(dateTime.suffixedFormattedDate(Formats.SCHEDULE_DATE_FORMAT))
    }
}
