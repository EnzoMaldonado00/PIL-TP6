package com.example.tp6.service.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id")
    var id: Int,
    @SerializedName("original_title")
    var originalTitle: String,
    @SerializedName("poster_path")
    var poster: String,
)
