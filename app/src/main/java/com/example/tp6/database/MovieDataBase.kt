package com.example.tp6.database

import com.example.tp6.database.dao.MovieDao
import com.example.tp6.database.mapper.mapToDataBaseMovie
import com.example.tp6.database.mapper.mapToLocalMovie
import com.example.tp6.service.model.Movie

interface MovieDataBase {
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun getAllMovies(): List<Movie>
}

class MovieDataBaseImpl(private val movieDao: MovieDao) : MovieDataBase {
    override suspend fun insertMovies(movies: List<Movie>) {
        movies.forEach { movie ->
            movieDao.insertMovie(movie.mapToDataBaseMovie())
        }
    }

    override suspend fun getAllMovies(): List<Movie> {
        return movieDao.getDBCharacters().mapToLocalMovie()
    }
}
