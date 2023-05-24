package com.example.tp6.domain.database

import com.example.tp6.domain.entity.Movie
import com.example.tp6.domain.util.CoroutineResult

interface MovieDataBase {
    suspend fun insertMovies(movies: List<Movie>)
    suspend fun getAllMovies(): CoroutineResult<List<Movie>>
}
