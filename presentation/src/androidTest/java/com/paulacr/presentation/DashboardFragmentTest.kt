package com.paulacr.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.paulacr.presentation.dashboard.DashboardFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class DashboardFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltRule.inject()
        MockKAnnotations.init(this, true)
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
