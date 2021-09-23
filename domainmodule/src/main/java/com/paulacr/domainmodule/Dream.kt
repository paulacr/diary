package com.paulacr.domain

import java.time.LocalDateTime

data class Dream(
    val dateTime: LocalDateTime,
    val description: String,
    val emoji: Int? = null
)