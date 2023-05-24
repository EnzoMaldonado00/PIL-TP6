package com.example.tp6.data.service

import com.example.tp6.data.service.api.MovieApi
import com.example.tp6.data.service.util.transformToList
import com.example.tp6.domain.entity.Movie
import com.example.tp6.domain.service.MovieService
import com.example.tp6.domain.util.CoroutineResult
import java.lang.Exception

class MovieServiceImpl(private val api: MovieRequestGenerator) : MovieService {

    override suspend fun getMovies(): CoroutineResult<List<Movie>> {
        try {
            val response = api.createService(MovieApi::class.java).getData().execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it.transformToList())
                }
            }
            return CoroutineResult.Failure(Exception(response.errorBody().toString()))
        } catch (e: Exception) {
            return CoroutineResult.Failure(e)
        }
    }
}
