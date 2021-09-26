package com.paulacr.presentation

import com.paulacr.domain.Dream
import java.time.LocalDateTime

object MockDreams {

    val dreamsList = listOf(
        Dream(1, LocalDateTime.now(), "some description dream", null)
    )
}