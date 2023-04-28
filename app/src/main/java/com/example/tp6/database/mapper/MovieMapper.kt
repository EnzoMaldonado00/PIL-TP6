package com.example.tp6.database.mapper

import com.example.tp6.database.entity.MovieEntity
import com.example.tp6.service.model.Movie

fun Movie.mapToDataBaseMovie(): MovieEntity =
    MovieEntity(
        id = id,
        originalTitle = originalTitle,
        poster = poster,
    )

fun List<MovieEntity>.mapToLocalMovie(): List<Movie> =
    map { entity ->
        Movie(
            id = entity.id,
            originalTitle = entity.originalTitle,
            poster = entity.poster,
        )
    }
