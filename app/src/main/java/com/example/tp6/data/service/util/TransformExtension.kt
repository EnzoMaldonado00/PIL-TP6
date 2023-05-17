package com.example.tp6.data.service.util

import com.example.tp6.data.entity.MovieEntity
import com.example.tp6.data.service.response.ResultResponse
import com.example.tp6.domain.entity.Movie

fun ResultResponse.transformToList(): List<Movie> {
    val movieList = mutableListOf<Movie>()
    movies.forEach() {
        movieList.add(
            Movie(
                it.id,
                it.title,
                it.poster,
                it.overview,
                it.releaseDate,
            ),
        )
    }
    return movieList
}

fun MovieEntity.toMovie() = Movie(this.id, this.title, this.poster, this.overview, this.releaseDate)

fun Movie.toMovieDB() = MovieEntity(this.id, this.title, this.poster, this.overview, this.releaseDate)

fun List<MovieEntity>.toMovieList() = this.map { it.toMovie() }
