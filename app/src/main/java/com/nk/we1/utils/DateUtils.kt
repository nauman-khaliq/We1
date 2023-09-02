/*
 * MIT License
 *
 * Copyright (c) 2022 Nauman Khaliq
 *
 */
package com.nk.we1.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    /**
     * Formats the current system time
     */
    fun formatCurrentTime(): String {
        return SimpleDateFormat("hh:mm a", Locale.getDefault()).format(Date())
    }
    /**
     * Formats the current system date
     */
    fun formatCurrentDate(): String {
        return SimpleDateFormat("dd MMM, EEE", Locale.getDefault()).format(Date())
    }
    /**
     * Formats date and time return only time
     * @return [String] Formatted time
     */
    fun formatDateTime(date: String): String? {
        return try {
            var format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
            format.timeZone = TimeZone.getTimeZone("UTC")
            val newDate = format.parse(date)
            //format = SimpleDateFormat("dd-M-yyyy hh:mm a")
            format = SimpleDateFormat("hh:mm a")
            format.timeZone = TimeZone.getDefault()
            format.format(newDate)
        } catch (ex: Exception) {
            null
        }

    }
}