package com.paulacr.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.paulacr.presentation.dashboard.DashboardFragment
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardFragmentTest {

    @Before
    fun init() {
//        MockKAnnotations.init(this, true)
    }

    @Test
    fun showEmptyStateWhenThereAreNoData() {
        launchFragmentInContainer<DashboardFragment>(
            initialState = Lifecycle.State.RESUMED
        )
        // Given

        // When

        // Then
        onView(withId(R.id.testText)).check(matches(isDisplayed()))
    }
}
