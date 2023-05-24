package com.example.tp6.domain.usecase

import com.example.tp6.domain.database.MovieDataBase
import com.example.tp6.domain.entity.Movie
import com.example.tp6.domain.service.MovieService
import com.example.tp6.domain.util.CoroutineResult

interface GetMoviesUseCase {
    suspend operator fun invoke(): CoroutineResult<List<Movie>>
}

class GetMoviesUseCaseImpl(
    private val movieService: MovieService,
    private val dataBase: MovieDataBase,
) : GetMoviesUseCase {
    override suspend fun invoke(): CoroutineResult<List<Movie>> {
        return when (val movies = movieService.getMovies()) {
            is CoroutineResult.Success -> {
                dataBase.insertMovies(movies.data)
                dataBase.getAllMovies()
            }
            is CoroutineResult.Failure -> {
                dataBase.getAllMovies()
            }
        }
    }
}
