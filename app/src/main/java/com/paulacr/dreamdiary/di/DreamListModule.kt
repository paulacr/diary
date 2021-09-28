package com.paulacr.dreamdiary.di

import com.paulacr.datamodule.database.DreamDao
import com.paulacr.datamodule.repository.DreamListRepositoryImpl
import com.paulacr.domain.DreamListRepository
import com.paulacr.domain.DreamUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DreamListModule {

    @Provides
    fun provideDreamRepository(dreamDao: DreamDao): DreamListRepository {
        return DreamListRepositoryImpl(dreamDao)
    }

    @Provides
    fun provideUseCase(repository: DreamListRepository): DreamUseCase {
        return DreamUseCase(repository)
    }
}
