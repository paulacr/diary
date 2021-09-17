package com.paulacr.dreamdiary.ui.views

import java.time.LocalDate
import java.time.LocalTime

object MockLocalDateTime {
    val currentDate: LocalDate = LocalDate.of(
        2021,
        5,
        23
    )

    val firstSavedDate: LocalDate = LocalDate.of(
        currentDate.year,
        currentDate.minusMonths(3).month,
        currentDate.dayOfMonth)

    val firstLocalTime: LocalTime = LocalTime.of(3, 22, 10)

    val lastSavedDate: LocalDate = LocalDate.of(
        currentDate.year,
        currentDate.plusMonths(3).month,
        currentDate.dayOfMonth)

    val lastLocalTime: LocalTime = LocalTime.of(3, 22, 10)
}