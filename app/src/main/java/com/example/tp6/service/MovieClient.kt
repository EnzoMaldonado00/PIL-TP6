package com.example.tp6.service

import com.example.tp6.service.model.MovieList
import retrofit2.Call
import retrofit2.http.GET

interface MovieClient {
    @GET("/3/movie/popular") // ADD BEARER
    fun getData(): Call<MovieList>
}
