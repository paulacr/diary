package com.paulacr.presentation.diary

import com.jraska.livedata.test
import com.paulacr.domain.Dream
import com.paulacr.domain.DreamUseCase
import com.paulacr.presentation.MockObjects
import com.paulacr.presentation.ViewState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class DreamsListViewModelTest : BaseViewModelTest() {

    @MockK
    private lateinit var useCase: DreamUseCase
    lateinit var viewModel: DreamsListViewModel

    override fun init() {
        super.init()
        viewModel = DreamsListViewModel(useCase)
    }

    @Test
    fun shouldReturnSuccessWhenContainsListOfDreams() {
        coEvery { useCase() } returns listOf(MockObjects.dream)
        val dreams = listOf(MockObjects.dream)
        val test = viewModel.dreamsLiveData.test()

        runBlockingTest {
            viewModel.getDreams()
        }

        test
            .assertValueHistory(
                ViewState.Loading(),
                ViewState.Success(dreams)
            )

        coVerify { useCase() }
    }

    @Test
    fun shouldReturnEmptyDreamsListWhenThereAreNoDreamsRecorded() {
        coEvery { useCase() } returns listOf()
        val expectedEmptyDreams = listOf<Dream>()
        val test = viewModel.dreamsLiveData.test()

        runBlockingTest {
            viewModel.getDreams()
        }

        test
            .assertValueHistory(
                ViewState.Loading(),
                ViewState.Success(expectedEmptyDreams)
            )

        coVerify { useCase() }
    }
}
