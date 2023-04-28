package com.example.tp6.service

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRequestGenerator {
    private const val MOVIE_API_URL = "https://developers.themoviedb.org"

    private val httpClient = OkHttpClient.Builder()

    private val builder = Retrofit.Builder()
        .baseUrl(MOVIE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }
}
