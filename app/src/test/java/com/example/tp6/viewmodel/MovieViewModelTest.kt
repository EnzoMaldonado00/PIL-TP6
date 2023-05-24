package com.example.tp6.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tp6.domain.entity.Movie
import com.example.tp6.domain.util.CoroutineResult
import com.example.tp6.presentation.mvvm.model.MovieModel
import com.example.tp6.presentation.mvvm.viewmodel.MovieViewModel
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
class MovieViewModelTest {

    @MockK
    private lateinit var model: MovieModel

    @MockK
    private lateinit var movies: List<Movie>

    private lateinit var movieViewModel: MovieViewModel

    private val testDispatcher = UnconfinedTestDispatcher()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        movieViewModel = MovieViewModel(model)
    }

    @After
    fun after() {
        Dispatchers.resetMain()
    }

    @Test
    fun `set status SHOW_INFO when coroutine result is successful`() {
        coEvery { model.getMovies() } returns CoroutineResult.Success(movies)

        runBlocking {
            movieViewModel.callService().join()
        }

        assertEquals(MovieViewModel.MovieStatus.SHOW_INFO, movieViewModel.getValue().value?.status)
        assertEquals(movies, movieViewModel.getValue().value?.movies)
    }

    @Test
    fun `set status ERROR when coroutine result is failure`() {
        coEvery { model.getMovies() } returns CoroutineResult.Failure(Exception())

        runBlocking {
            movieViewModel.callService().join()
        }

        assertEquals(MovieViewModel.MovieStatus.ERROR, movieViewModel.getValue().value?.status)
    }
}
