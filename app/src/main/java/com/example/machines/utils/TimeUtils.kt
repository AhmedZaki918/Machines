package com.example.machines.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.machines.data.local.Constants
import com.example.machines.data.local.Constants.COLUMN
import com.example.machines.data.local.Constants.DOT
import com.example.machines.data.local.Constants.MINUTES_RESET
import com.example.machines.data.local.Constants.TWENTY_FOUR
import com.example.machines.data.local.Format
import com.example.machines.data.local.Number
import com.example.machines.ui.Time
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt


@RequiresApi(Build.VERSION_CODES.O)
fun differentInTwoTimes(startTime: String, endTime: String): String {
    val rh: Time = if (isFirstTimeBigger(startTime, endTime)) {
        diffInTwoTimesInTwoDays(startTime, endTime)
    } else convertMinutesToTime(differentInMinutes(startTime, endTime))
    return formatTime(rh.hours) + COLUMN + formatTime(rh.minutes)
}

@RequiresApi(Build.VERSION_CODES.O)
fun differentInMinutes(time1: String, time2: String): Long {
    val simpleDateFormat = SimpleDateFormat(Constants.TIME_FORMAT, Locale.ENGLISH)
    val date1 = convertToLocalDateTimeViaInstant(simpleDateFormat.parse(time1)!!)
    val date2 = convertToLocalDateTimeViaInstant(simpleDateFormat.parse(time2)!!)
    return Duration.between(date1, date2).toMinutes()
}


fun convertMinutesToTime(durationInMinutes: Long): Time {
    val hours: String
    val minutes: String

    return if (durationInMinutes.mod(60) == 0) {
        // Whole number
        hours = (durationInMinutes / 60).toString()
        minutes = MINUTES_RESET
        Time(hours, minutes)

    } else {
        // Decimal number
        val result = (durationInMinutes.toFloat() / 60f).toString()
        hours = result.substring(0, result.indexOf(DOT))
        minutes = result.substring(result.indexOf(DOT), result.lastIndex + 1)

        val convertMinutes = (minutes.toFloat() * 60f).roundToInt()
        Time(hours, convertMinutes.toString())
    }
}


fun isFirstTimeBigger(time1: String, time2: String): Boolean {
    val hoursInTime1 = time1.substring(0, time1.indexOf(COLUMN)).toInt()
    val hoursInTime2 = time2.substring(0, time2.indexOf(COLUMN)).toInt()
    return hoursInTime1 > hoursInTime2
}


@RequiresApi(Build.VERSION_CODES.O)
fun diffInTwoTimesInTwoDays(time: String, time2: String): Time {
    val diffInMinutesUntilEndOfDay = differentInMinutes(time, TWENTY_FOUR)
    val endTimeInMinutes = convertTimeToMinutes(time2)
    val totalMinutes = diffInMinutesUntilEndOfDay + endTimeInMinutes
    return convertMinutesToTime(totalMinutes)
}

fun convertTimeToMinutes(time: String): Int {
    val hoursInTime = time.substring(0, time.indexOf(COLUMN)).toInt()
    val minutesInTime =
        time.substring(time.indexOf(COLUMN) + 1, time.lastIndex + 1).toInt()
    return (hoursInTime * 60) + minutesInTime
}


fun currentTime(): String {
    return SimpleDateFormat(Constants.TIME_FORMAT, Locale.ENGLISH).format(Date())
}


@RequiresApi(Build.VERSION_CODES.O)
fun convertToLocalDateTimeViaInstant(dateToConvert: Date): LocalDateTime {
    return dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDateTime()
}

fun formatTime(time: String): String {
    return when (time) {
        Number.ZERO.value -> Format.ZERO_ZERO.value
        Number.ONE.value -> Format.ZERO_ONE.value
        Number.TWO.value -> Format.ZERO_TWO.value
        Number.THREE.value -> Format.ZERO_THREE.value
        Number.FOUR.value -> Format.ZERO_FOUR.value
        Number.FIVE.value -> Format.ZERO_FIVE.value
        Number.SEX.value -> Format.ZERO_SEX.value
        Number.SEVEN.value -> Format.ZERO_SEVEN.value
        Number.EIGHT.value -> Format.ZERO_EIGHT.value
        Number.NINE.value -> Format.ZERO_NINE.value
        else -> time
    }
}