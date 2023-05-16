package com.example.tp6.data.service.api

import com.example.tp6.data.service.response.ResultResponse
import retrofit2.Call
import retrofit2.http.GET

interface MovieApi {
    @GET("/3/movie/popular")
    fun getData(): Call<ResultResponse>
}
