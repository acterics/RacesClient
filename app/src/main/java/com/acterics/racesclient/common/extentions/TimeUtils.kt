package com.acterics.racesclient.common.extentions

import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat

/**
 * Created by root on 15.10.17.
 */

fun DateTime.suffixedFormattedDate(suffixedFormat: String): String {
    return formattedDate(String.format(suffixedFormat, dayOfMonth.getNumberSuffix()))
}

fun DateTime.formattedDate(format: String): String {
    return DateTimeFormat.forPattern(format).print(this)
}


fun Int.getNumberSuffix(): String {
    return when (this) {
        in 11..13 -> "th"
        else -> when(this % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
        }
    }
}