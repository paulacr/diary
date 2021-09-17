package com.paulacr.dreamdiary.ui.views

import com.paulacr.dreamdiary.ui.views.MockLocalDateTime.currentDate
import com.paulacr.dreamdiary.ui.views.MockLocalDateTime.firstLocalTime
import com.paulacr.dreamdiary.ui.views.MockLocalDateTime.firstSavedDate
import com.paulacr.dreamdiary.ui.views.MockLocalDateTime.lastLocalTime
import com.paulacr.dreamdiary.ui.views.MockLocalDateTime.lastSavedDate
import junit.framework.Assert.*
import org.junit.Test
import java.time.LocalDateTime

class MonthSelectorManagerTest {

    @Test
    fun enableLeftArrowWhenThereArePreviousMonths() {

        val localDateTimes = listOf<LocalDateTime>(
            LocalDateTime.of(firstSavedDate, firstLocalTime)
        )

        val currentDateTime = LocalDateTime.of(currentDate, firstLocalTime)
        MonthSelectorManager().buildMonthSelector(currentDateTime, localDateTimes) {
            leftArrow, rightArrow ->

            assertTrue(leftArrow)
            assertFalse(rightArrow)
        }
    }

    @Test
    fun enableRightArrowWhenThereAreForwardMonths() {

        val localDateTimes = listOf<LocalDateTime>(
            LocalDateTime.of(lastSavedDate, lastLocalTime)
        )

        val currentDateTime = LocalDateTime.of(currentDate, firstLocalTime)
        MonthSelectorManager().buildMonthSelector(currentDateTime, localDateTimes) {
                leftArrow, rightArrow ->

            assertFalse(leftArrow)
            assertTrue(rightArrow)
        }
    }

    @Test
    fun enableLeftAndRightArrowWhenThereAreMonthsBeforeAndAfter() {

        val localDateTimes = listOf<LocalDateTime>(
            LocalDateTime.of(firstSavedDate, firstLocalTime),
            LocalDateTime.of(lastSavedDate, lastLocalTime)
        )
        val currentDateTime = LocalDateTime.of(currentDate, firstLocalTime)
        MonthSelectorManager().buildMonthSelector(currentDateTime, localDateTimes) {
                leftArrow, rightArrow ->

            assertTrue(leftArrow)
            assertTrue(rightArrow)
        }
    }

    @Test
    fun buildDateTextTest() {
        val datetime = LocalDateTime.of(MockLocalDateTime.currentDate, firstLocalTime)
        val dateText = MonthSelectorManager().buildMonthWithYearText(datetime)

        assertEquals("May / 2021", dateText)
    }
}