package com.paulacr.dreamdiary.di

import com.paulacr.dreamdiary.data.database.DreamDao
import com.paulacr.dreamdiary.data.repository.DreamListRepository
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