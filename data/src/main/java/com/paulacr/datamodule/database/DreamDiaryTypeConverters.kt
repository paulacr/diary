package com.paulacr.datamodule.database

import androidx.room.TypeConverter
import java.time.LocalDateTime

class DreamDiaryTypeConverters {

    @TypeConverter
    fun toDate(dateString: String): LocalDateTime {
        return LocalDateTime.parse(dateString)
    }

    @TypeConverter
    fun toDateString(date: LocalDateTime): String {
        return date.toString()
    }
}