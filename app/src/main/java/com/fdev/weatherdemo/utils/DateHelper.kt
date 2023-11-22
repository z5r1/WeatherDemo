package com.fdev.weatherdemo.utils

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

private const val OUTPUT_FORMAT_WEAK_DAY_MONTH = "dd.MM, EEEE"
private const val OUTPUT_FORMAT_HOUR = "hh:mm"
private val timestampInputFormats = listOf("yyyy-MM-dd hh:mm", "yyyy-MM-dd")

object DateHelper {
    fun getFormattedDateForDays(timestamp: String) =
        getFormattedDate(timestamp, OUTPUT_FORMAT_WEAK_DAY_MONTH)

    fun getFormattedDateForHours(timestamp: String) =
        getFormattedDate(timestamp, OUTPUT_FORMAT_HOUR)

    private fun getFormattedDate(
        timestamp: String,
        outputFormat: String,
    ): String {
        val dateFormatter = SimpleDateFormat(outputFormat, Locale.getDefault())
        dateFormatter.timeZone = TimeZone.getTimeZone("GMT")

        for (dateFormat in timestampInputFormats) {
            try {
                val format = SimpleDateFormat(dateFormat, Locale.getDefault())
                val date = format.parse(timestamp)
                if (date != null) {
                    dateFormatter.timeZone = TimeZone.getDefault()
                    return dateFormatter.format(date)
                }
            } catch (e: ParseException) {
                Log.d(DateHelper::class.simpleName, e.message.toString())
            }
        }

        return timestamp
    }
}