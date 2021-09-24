package com.paulacr.datamodule.repository

import com.paulacr.datamodule.database.DreamDao
import com.paulacr.datamodule.model.DreamMapper
import com.paulacr.domain.Dream
import com.paulacr.domain.DreamListRepository
import javax.inject.Inject

class DreamListRepositoryImpl @Inject constructor(
    private val dreamDao: DreamDao
): DreamListRepository {
    override suspend fun getDreams(): List<Dream>? {
        return DreamMapper.map(dreamDao.getAll())
    }

    override suspend fun addDream(dream: Dream) {
        return dreamDao.addDream(DreamMapper.map(dream))
    }

    override suspend fun removeDream(dream: Dream) {
        return dreamDao.deleteDream(DreamMapper.map(dream))
    }

    override suspend fun removeDreamById(dreamId: Long) {
        return dreamDao.deleteDreamById(dreamId)
    }

}