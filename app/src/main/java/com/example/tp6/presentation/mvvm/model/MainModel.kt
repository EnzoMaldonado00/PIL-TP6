package com.example.tp6.presentation.mvvm.model

import com.example.tp6.domain.entity.Movie
import com.example.tp6.domain.usecase.GetMoviesUseCase
import com.example.tp6.domain.util.CoroutineResult

class MainModel(private val getMoviesUseCase: GetMoviesUseCase) {
    fun getMovies(): CoroutineResult<List<Movie>> = getMoviesUseCase()
}
