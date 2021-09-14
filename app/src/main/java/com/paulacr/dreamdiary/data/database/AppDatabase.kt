package com.paulacr.dreamdiary.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paulacr.dreamdiary.data.model.Dream

@TypeConverters(DreamDiaryTypeConverters::class)
@Database(entities = arrayOf(Dream::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dreamDao(): DreamDao
}