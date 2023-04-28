package com.example.tp6.service

import com.example.tp6.service.model.MovieList
import com.example.tp6.util.CoroutineResult
import java.lang.Exception

interface MovieService {
    suspend fun getMovies(): CoroutineResult<MovieList>
}

class MovieServiceImpl(private val client: MovieClient) : MovieService {

    override suspend fun getMovies(): CoroutineResult<MovieList> {
        try {
            val response = client.getData().execute()
            if (response.isSuccessful) {
                response.body()?.let {
                    return CoroutineResult.Success(it)
                }
            }
            return CoroutineResult.Failure(Exception(response.errorBody().toString()))
        } catch (e: Exception) {
            return CoroutineResult.Failure(e)
        }
    }
}
