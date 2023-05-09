package com.example.tp6

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tp6.mvvm.contract.MainContract
import com.example.tp6.mvvm.viewmodel.MainViewModel
import com.example.tp6.service.model.Movie
import com.example.tp6.util.CoroutineResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import java.lang.Exception

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @MockK
    private lateinit var model: MainContract.Model

    @MockK
    private lateinit var movies: List<Movie>

    private lateinit var mainViewModel: MainContract.ViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        mainViewModel = MainViewModel(model)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `set status SHOW_INFO when coroutine result is successful`() {
        coEvery { model.getMovies() } returns CoroutineResult.Success(movies)

        runBlocking {
            mainViewModel.callService().join()
        }

        assertEquals(MainViewModel.MainStatus.SHOW_INFO, mainViewModel.getValue().value?.status)
        assertEquals(movies, mainViewModel.getValue().value?.movies)
    }

    @Test
    fun `set status ERROR when coroutine result is failure`() {
        coEvery { model.getMovies() } returns CoroutineResult.Failure(Exception())

        runBlocking {
            mainViewModel.callService().join()
        }

        assertEquals(MainViewModel.MainStatus.ERROR, mainViewModel.getValue().value?.status)
    }
}
