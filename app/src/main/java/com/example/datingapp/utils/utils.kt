package com.example.datingapp.utils

import android.content.Context
import android.util.Log
import java.time.Duration
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun dpToPx(context: Context, dp: Float): Int {

    val density = context.resources.displayMetrics.density
    return (dp * density + 0.5f).toInt()
}

fun extractDayFromDate(dateStampDate : String) : String {

    val currentDate = DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()).toString().substring(0..9)

    // Keeping in mind, date string is of the type YYYY:MM:DD
    val dateStampYear = dateStampDate.substring(0..3)
    val dateStampMonth = dateStampDate.substring(5..6)
    val dateStampDay = dateStampDate.substring(8..9)

    val difference = getDaysDifference(dateStampDate, currentDate)
    return if(difference > 7L) {
        dateStampDay + " " + Constants.monthsOfTheYear[(dateStampMonth).toInt()-1] + "'${dateStampYear}"
    }
    else if(difference == 0L) "Today"
    else if(difference == 1L) "Yesterday"
    else {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val dayOfTheWeek = LocalDate.parse(dateStampDate, formatter).dayOfWeek.toString()
        titleCaseString(dayOfTheWeek)
    }
}

fun getDaysDifference(date1String: String, date2String: String): Long {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val date1 = LocalDate.parse(date1String, formatter)
    val date2 = LocalDate.parse(date2String, formatter)
    val duration = Duration.between(date1.atStartOfDay(), date2.atStartOfDay())
    return duration.toDays()
}

fun titleCaseString(string : String) : String {
    val initialLetter = string[0].uppercase()
    val restOfTheString = string.substring(1).lowercase()
    return initialLetter + restOfTheString
}