package com.paulacr.domain

interface DreamListRepository {

    suspend fun getDreams(): List<Dream>?

    suspend fun addDream(dream: Dream)

    suspend fun removeDream(dream: Dream)

    suspend fun removeDreamById(dreamId: Long)
}
