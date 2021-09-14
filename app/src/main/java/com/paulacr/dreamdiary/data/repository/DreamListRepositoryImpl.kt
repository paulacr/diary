package com.paulacr.dreamdiary.data.repository

import com.paulacr.dreamdiary.data.model.Dream
import com.paulacr.dreamdiary.data.database.DreamDao
import javax.inject.Inject

class DreamListRepositoryImpl @Inject constructor(
    private val dreamDao: DreamDao
): DreamListRepository {
    override suspend fun getDreams(): List<Dream>? {
        return dreamDao.getAll()
    }

    override suspend fun addDream(dream: Dream) {
        println("Dreams -> add dream $dream")
        return dreamDao.addDream(dream)
    }

    override suspend fun removeDream(dream: Dream) {
        println("Dreams -> remove dream $dream")
        return dreamDao.deleteDream(dream)
    }

    override suspend fun removeDreamById(dreamId: Long) {
        println("Dreams -> remove dream by id $dreamId")
        return dreamDao.deleteDreamById(dreamId)
    }


}