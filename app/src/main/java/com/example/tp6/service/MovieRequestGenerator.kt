package com.example.tp6.service

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MovieRequestGenerator {
    private const val MOVIE_API_URL = "https://api.themoviedb.org"
    private const val accessToken =
        "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJiYWUxNjliZDQwYjZiNDhjNzFjMjM3YmRlNjAxNDBhNyIsInN1YiI6IjY0NGFlYTU4MGMxMjU1MDVkNDdhOTQ3OSIsInNjb3B" +
            "lcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.weFV6B5AdhaIsiu-0aPskJOfyIGNL0KKqp0N5HODmSQ"

    class OAuthInterceptor(private val tokenType: String, private val accessToken: String) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            var request = chain.request()
            request = request.newBuilder().header("Authorization", "$tokenType $accessToken").build()

            return chain.proceed(request)
        }
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(OAuthInterceptor("Bearer", accessToken))
        .build()

    private val builder = Retrofit.Builder()
        .baseUrl(MOVIE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient).build()
        return retrofit.create(serviceClass)
    }
}
