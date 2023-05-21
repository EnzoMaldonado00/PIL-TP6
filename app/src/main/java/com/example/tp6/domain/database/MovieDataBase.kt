package com.example.tp6.domain.database

import com.example.tp6.domain.entity.Movie
import com.example.tp6.domain.util.CoroutineResult

interface MovieDataBase {
    fun insertMovies(movies: List<Movie>)
    fun getAllMovies(): CoroutineResult<List<Movie>>
}
