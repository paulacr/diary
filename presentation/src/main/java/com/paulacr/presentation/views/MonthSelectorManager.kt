package com.paulacr.presentation.views

import java.time.LocalDateTime

class MonthSelectorManager {

    private val currentDateTime: LocalDateTime = LocalDateTime.now()

    fun buildMonthWithYearText(localDateTime: LocalDateTime): String {
        return localDateTime.month.name
            .take(3)
            .lowercase()
            .replaceFirstChar {
                it.uppercase()
            }
            .plus(" / ")
            .plus(localDateTime.year)
    }

    fun buildMonthSelector(currentTimeStamp: LocalDateTime = currentDateTime, localDateTimeList: List<LocalDateTime>, callback: (enableLeftArrow: Boolean, enableRightArrow: Boolean) -> Unit) {

        val enableLeft = localDateTimeList.filter {
            it.isBefore(currentTimeStamp.minusMonths(1))
        }
        val enableRight = localDateTimeList.filter {
            it.isAfter(currentTimeStamp.plusMonths(1))
        }
        callback(enableLeft.isNotEmpty(), enableRight.isNotEmpty())
    }

    fun navigate(currentTimeStamp: LocalDateTime? = currentDateTime, direction: MonthDirection) {
        if (direction == MonthDirection.PREVIOUS) {
            currentTimeStamp?.minusMonths(1)
        } else {
            currentTimeStamp?.plusMonths(1)
        }
    }

    enum class MonthDirection {
        PREVIOUS,
        NEXT
    }
}
