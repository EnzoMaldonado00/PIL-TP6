package com.example.tp6.data.database

import com.example.tp6.data.service.util.toMovieDB
import com.example.tp6.data.service.util.toMovieList
import com.example.tp6.domain.database.MovieDataBase
import com.example.tp6.domain.entity.Movie
import com.example.tp6.domain.util.CoroutineResult

class MovieDataBaseImpl(private val movieDao: MovieDao) : MovieDataBase {
    override fun insertMovies(movies: List<Movie>) {
        movies.forEach { movie ->
            movieDao.insertMovie(movie.toMovieDB())
        }
    }

    override fun getAllMovies(): CoroutineResult<List<Movie>> =
        movieDao.getDBCharacters().let {
            if (it.isNotEmpty()) {
                CoroutineResult.Success(it.toMovieList())
            } else {
                CoroutineResult.Failure(Exception())
            }
        }
}
