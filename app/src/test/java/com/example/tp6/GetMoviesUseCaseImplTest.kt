package com.example.tp6

import com.example.tp6.domain.database.MovieDataBase
import com.example.tp6.domain.entity.Movie
import com.example.tp6.domain.service.MovieService
import com.example.tp6.domain.usecase.GetMoviesUseCase
import com.example.tp6.domain.usecase.GetMoviesUseCaseImpl
import com.example.tp6.domain.util.CoroutineResult
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
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
        every { movieService.getMovies() } returns CoroutineResult.Success(moviesList)
        every { database.getAllMovies() } returns CoroutineResult.Success(moviesList)

        val result = getMoviesUseCase()

        verify { database.insertMovies(moviesList) }
        verify { database.getAllMovies() }

        assertEquals(moviesList, (result as CoroutineResult.Success).data)
    }

    @Test
    fun `use case return failure & the database is not empty`() {
        every { movieService.getMovies() } returns CoroutineResult.Failure(Exception(ERROR_MESSAGE))
        every { database.getAllMovies() } returns CoroutineResult.Success(moviesList)

        val result = getMoviesUseCase()

        verify { database.getAllMovies() }

        assertEquals(moviesList, (result as CoroutineResult.Success).data)
    }

    @Test
    fun `use case return failure & the database is empty`() {
        every { movieService.getMovies() } returns CoroutineResult.Failure(Exception(ERROR_MESSAGE))
        every { database.getAllMovies() } returns CoroutineResult.Failure(Exception(ERROR_MESSAGE))

        val result = getMoviesUseCase()

        verify { database.getAllMovies() }

        assertEquals(ERROR_MESSAGE, (result as CoroutineResult.Failure).exception.message)
    }

    companion object {
        private const val ERROR_MESSAGE = "Error"
    }
}
