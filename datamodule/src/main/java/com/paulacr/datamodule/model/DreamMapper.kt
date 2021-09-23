package com.paulacr.datamodule.model

import com.paulacr.domain.Dream

object DreamMapper {

    fun map(dreams: List<DreamEntity>?): List<Dream>? {
        return dreams?.map {
            Dream(dateTime = it.dateTime,
            description = it.description,
            emoji = it.emoji)
        }
    }
}