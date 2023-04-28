package com.example.tp6.mvvm.model

import com.example.tp6.database.MovieDataBase
import com.example.tp6.mvvm.contract.MainContract
import com.example.tp6.service.MovieService
import com.example.tp6.service.model.Movie
import com.example.tp6.util.CoroutineResult

class MainModel(private val service: MovieService, private val database: MovieDataBase) : MainContract.Model {
    override suspend fun getMovies(): CoroutineResult<List<Movie>> {
        return when (val movies = service.getMovies()) {
            is CoroutineResult.Success -> {
                database.insertMovies(movies.data.movies)
                CoroutineResult.Success(database.getAllMovies())
            }
            is CoroutineResult.Failure -> {
                CoroutineResult.Success(database.getAllMovies())
            }
        }
    }
}
