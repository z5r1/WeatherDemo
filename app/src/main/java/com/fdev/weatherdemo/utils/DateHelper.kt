package com.fdev.weatherdemo.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

private const val TIMESTAMP_FORMAT = "yyyy-MM-dd"
private const val OUTPUT_FORMAT = "EEEE, dd.MM"
object DateHelper {
    fun getFormattedDate(
        timestamp: String,
    ): String {

        val dateFormatter = SimpleDateFormat(OUTPUT_FORMAT, Locale.getDefault())
        dateFormatter.timeZone = TimeZone.getTimeZone("GMT")

        val parser = SimpleDateFormat(TIMESTAMP_FORMAT, Locale.getDefault())
        parser.timeZone = TimeZone.getTimeZone("GMT")

        try {
            val date = parser.parse(timestamp)
            if (date != null) {
                dateFormatter.timeZone = TimeZone.getDefault()
                return dateFormatter.format(date)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return timestamp
    }
}