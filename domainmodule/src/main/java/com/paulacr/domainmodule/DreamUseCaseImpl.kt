package com.paulacr.domainmodule

import com.paulacr.datamodule.model.DreamMapper
import com.paulacr.datamodule.repository.DreamListRepository
import com.paulacr.domain.Dream
import javax.inject.Inject

class DreamUseCaseImpl @Inject constructor(private val dreamRepository: DreamListRepository) : DreamUseCase {

    override suspend fun getDreams(): List<Dream>? {
        return DreamMapper.map(dreamRepository.getDreams())
    }
}