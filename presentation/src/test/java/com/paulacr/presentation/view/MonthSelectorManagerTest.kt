package com.paulacr.presentation.view

import com.paulacr.presentation.MockObjects.currentDate
import com.paulacr.presentation.MockObjects.firstLocalTime
import com.paulacr.presentation.MockObjects.firstSavedDate
import com.paulacr.presentation.MockObjects.lastLocalTime
import com.paulacr.presentation.MockObjects.lastSavedDate
import com.paulacr.presentation.views.MonthSelectorManager
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
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
        val datetime = LocalDateTime.of(currentDate, firstLocalTime)
        val dateText = MonthSelectorManager().buildMonthWithYearText(datetime)

        assertEquals("May / 2021", dateText)
    }
}
