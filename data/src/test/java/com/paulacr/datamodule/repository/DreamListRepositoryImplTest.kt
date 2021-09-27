package com.paulacr.datamodule.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.paulacr.datamodule.database.DreamDao
import com.paulacr.datamodule.model.DreamEntity
import com.paulacr.datamodule.model.DreamMapper
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.time.LocalDateTime

@ExperimentalCoroutinesApi
class DreamListRepositoryImplTest {

    @MockK
    private lateinit var dao: DreamDao
    lateinit var repository: DreamListRepositoryImpl

    @get:Rule
    val testRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        MockKAnnotations.init(this, true)
        repository = DreamListRepositoryImpl(dao)
    }

    @Test
    fun getDreams() = runBlockingTest {
        coEvery { dao.getAll() } returns listOf(MockObjects.dream)

        val result = repository.getDreams()
        val expectedDreams = DreamMapper.map(
            listOf(
                DreamEntity(
                    LocalDateTime.of(MockObjects.currentDate, MockObjects.firstLocalTime),
                    "bla bla bla pi pi pi po po po",
                    null
                )
            )
        )

        coVerify { dao.getAll() }
        assertEquals(result, expectedDreams)
    }

    @Test
    fun whenDreamsTableEmptyShouldReturnEmptyDreamsList() = runBlockingTest {
        coEvery { dao.getAll() } returns listOf()

        val result = repository.getDreams()
        val expectedDreams = DreamMapper.map(emptyList())

        coVerify { dao.getAll() }
        assertEquals(result, expectedDreams)
    }
}
