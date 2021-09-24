package com.paulacr.domain

class DreamUseCase constructor(private val dreamRepository: DreamListRepository) {

    suspend operator fun invoke(): List<Dream>? {
        return dreamRepository.getDreams()
    }

    suspend operator fun invoke(dreamId: Long) {
        dreamRepository.removeDreamById(dreamId)
    }

    suspend operator fun invoke(dream: Dream) {
        dreamRepository.addDream(dream)
    }
}