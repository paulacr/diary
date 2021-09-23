package com.paulacr.datamodule.di

import android.content.Context
import androidx.room.Room
import com.paulacr.datamodule.database.DreamDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Provides
    fun providesDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "DreamDatabase"
        ).build()
    }

    @Provides
    fun providesDreamDao(appDatabase: AppDatabase): DreamDao {
        return appDatabase.dreamDao()
    }
}