package com.waddress.myweather.utils

import android.arch.persistence.room.TypeConverter
import android.os.Build
import android.support.annotation.RequiresApi
import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter

/**
 * Created by z.HAGUI.
 */

object TimeTypeConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    @JvmStatic
    fun toOffsetDateTime(value: String?): OffsetDateTime? {
        return value?.let {
            return formatter.parse(value, OffsetDateTime::from)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    @JvmStatic
    fun fromOffsetDateTime(date: OffsetDateTime?): String? {
        return date?.format(formatter)
    }
}