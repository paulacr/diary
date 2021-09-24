package com.paulacr.domain

import java.io.Serializable
import java.time.LocalDateTime

data class Dream(
    val id: Long? = 0,
    val dateTime: LocalDateTime,
    val description: String,
    val emoji: Int? = null
): Serializable