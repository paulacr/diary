package com.paulacr.datamodule.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity
data class DreamEntity(
    @PrimaryKey(autoGenerate = true) var id: Long? = 0L,
    val dateTime: LocalDateTime,
    val description: String,
    val emoji: Int? = null
) : BaseModel()
