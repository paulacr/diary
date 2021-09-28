package com.paulacr.datamodule.model

object DreamMapper {

    fun map(dreams: List<DreamEntity>?): List<com.paulacr.domain.Dream>? {
        return dreams?.map {
            com.paulacr.domain.Dream(
                id = it.id,
                dateTime = it.dateTime,
                description = it.description,
                emoji = it.emoji
            )
        }
    }

    fun map(dream: com.paulacr.domain.Dream): DreamEntity {
        return DreamEntity(
            dateTime = dream.dateTime,
            description = dream.description,
            emoji = dream.emoji
        )
    }
}
