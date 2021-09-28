package com.paulacr.dreamdiary.di

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.paulacr.datamodule.database.DreamDao
import com.paulacr.datamodule.database.DreamDiaryTypeConverters
import com.paulacr.datamodule.model.DreamEntity

@TypeConverters(DreamDiaryTypeConverters::class)
@Database(entities = arrayOf(DreamEntity::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dreamDao(): DreamDao
}
