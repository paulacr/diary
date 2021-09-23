package com.paulacr.datamodule.repository

import com.paulacr.datamodule.model.DreamEntity

interface DreamListRepository {

    suspend fun getDreams(): List<DreamEntity>?

    suspend fun addDream(dream: DreamEntity)

    suspend fun removeDream(dream: DreamEntity)

    suspend fun removeDreamById(dreamId: Long)

}