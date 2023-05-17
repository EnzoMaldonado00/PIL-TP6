package com.example.tp6.presentation.mvvm.model

import com.example.tp6.domain.entity.Movie
import com.example.tp6.domain.usecase.GetMovieUseCase
import com.example.tp6.util.CoroutineResult

class MainModel(private val getMovieUseCase: GetMovieUseCase) {
    suspend fun getMovies(): CoroutineResult<List<Movie>> = getMovieUseCase()
}
