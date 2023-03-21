package com.example.english_dictionary.data.database

import androidx.room.TypeConverter
import java.util.*

class DateConverters {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return if (date == null) null else date.time
    }
}