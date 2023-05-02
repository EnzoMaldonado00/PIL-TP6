package com.example.tp6.database.mapper

import com.example.tp6.database.entity.MovieEntity
import com.example.tp6.service.model.Movie

fun Movie.mapToDataBaseMovie(): MovieEntity =
    MovieEntity(
        id = id,
        title = title,
        poster = poster,
        overview = overview,
        releaseDate = releaseDate,
    )

fun List<MovieEntity>.mapToLocalMovie(): List<Movie> =
    map { entity ->
        Movie(
            id = entity.id,
            title = entity.title,
            poster = entity.poster,
            overview = entity.overview,
            releaseDate = entity.releaseDate,
        )
    }
