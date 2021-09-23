package com.paulacr.data.di

import com.paulacr.datamodule.database.DreamDao
import com.paulacr.datamodule.repository.DreamListRepository
import com.paulacr.dreamdiary.data.repository.DreamListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DreamListModule {

    @Provides
    fun provideDreamRepository(dreamDao: DreamDao) : DreamListRepository {
        return DreamListRepositoryImpl(dreamDao)
    }
}