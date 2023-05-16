package com.example.tp6.data.service.response

import com.google.gson.annotations.SerializedName

data class ResultResponse(
    @SerializedName("page")
    var page: Int,
    @SerializedName("results")
    val movies: List<MovieResponse>,
)
