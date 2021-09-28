package com.paulacr.presentation

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.paulacr.domain.DreamListRepository
import com.paulacr.domain.DreamUseCase
import com.paulacr.presentation.diary.DreamsListFragment
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class DreamsListFragmentTest {

    @Inject
    lateinit var useCase: DreamUseCase

    @Inject
    lateinit var repository: DreamListRepository

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
        hiltRule.inject()
    }

    @Test
    fun showEmptyStateWhenThereAreNoData() {
        launchFragmentInContainer<DreamsListFragment>(
            initialState = Lifecycle.State.INITIALIZED
        )
        // Given
        coEvery { useCase() } returns MockDreams.dreamsList

        // When

        // Then
        onView(withId(R.id.viewEmptyDreamsList)).check(matches(isDisplayed()))
    }
}
