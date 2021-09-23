package com.paulacr.domainmodule

import com.paulacr.datamodule.model.DreamEntity
import com.paulacr.domain.Dream

interface DreamUseCase {

    suspend fun getDreams(): List<Dream>?

    suspend fun removeDreamById(dreamId: Long)

    suspend fun addDream(dream: Dream)

}