package com.paulacr.dreamdiary.data.repository

import com.paulacr.datamodule.model.DreamEntity
import com.paulacr.datamodule.database.DreamDao
import com.paulacr.datamodule.repository.DreamListRepository
import javax.inject.Inject

class DreamListRepositoryImpl @Inject constructor(
    private val dreamDao: DreamDao
): DreamListRepository {
    override suspend fun getDreams(): List<DreamEntity>? {
        return dreamDao.getAll()
    }

    override suspend fun addDream(dream: DreamEntity) {
        println("Dreams -> add dream $dream")
        return dreamDao.addDream(dream)
    }

    override suspend fun removeDream(dream: DreamEntity) {
        println("Dreams -> remove dream $dream")
        return dreamDao.deleteDream(dream)
    }

    override suspend fun removeDreamById(dreamId: Long) {
        println("Dreams -> remove dream by id $dreamId")
        return dreamDao.deleteDreamById(dreamId)
    }


}