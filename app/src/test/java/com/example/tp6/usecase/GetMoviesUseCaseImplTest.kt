package com.example.tp6.usecase

import com.example.tp6.domain.database.MovieDataBase
import com.example.tp6.domain.entity.Movie
import com.example.tp6.domain.service.MovieService
import com.example.tp6.domain.usecase.GetMoviesUseCase
import com.example.tp6.domain.usecase.GetMoviesUseCaseImpl
import com.example.tp6.domain.util.CoroutineResult
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.lang.Exception

class GetMoviesUseCaseImplTest {

    @MockK
    private lateinit var movieService: MovieService

    @MockK
    private lateinit var database: MovieDataBase

    @MockK
    private lateinit var moviesList: List<Movie>

    private lateinit var getMoviesUseCase: GetMoviesUseCase

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxed = true)
        getMoviesUseCase = GetMoviesUseCaseImpl(movieService, database)
    }

    @Test
    fun `use case return success`() {
        coEvery { movieService.getMovies() } returns CoroutineResult.Success(moviesList)
        coEvery { database.getAllMovies() } returns CoroutineResult.Success(moviesList)

        val result = runBlocking { getMoviesUseCase() }

        coVerify { database.insertMovies(moviesList) }
        coVerify { database.getAllMovies() }

        assertEquals(moviesList, (result as CoroutineResult.Success).data)
    }

    @Test
    fun `use case return failure & the database is not empty`() {
        coEvery { movieService.getMovies() } returns CoroutineResult.Failure(Exception(ERROR_MESSAGE))
        coEvery { database.getAllMovies() } returns CoroutineResult.Success(moviesList)

        val result = runBlocking { getMoviesUseCase() }

        coVerify { database.getAllMovies() }

        assertEquals(moviesList, (result as CoroutineResult.Success).data)
    }

    @Test
    fun `use case return failure & the database is empty`() {
        coEvery { movieService.getMovies() } returns CoroutineResult.Failure(Exception(ERROR_MESSAGE))
        coEvery { database.getAllMovies() } returns CoroutineResult.Failure(Exception(ERROR_MESSAGE))

        val result = runBlocking { getMoviesUseCase() }

        coVerify { database.getAllMovies() }

        assertEquals(ERROR_MESSAGE, (result as CoroutineResult.Failure).exception.message)
    }

    companion object {
        private const val ERROR_MESSAGE = "Error"
    }
}
