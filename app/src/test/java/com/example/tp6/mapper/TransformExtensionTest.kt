package com.example.tp6.mapper

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tp6.data.entity.MovieEntity
import com.example.tp6.data.service.util.toMovie
import com.example.tp6.data.service.util.toMovieDB
import com.example.tp6.data.service.util.toMovieList
import com.example.tp6.domain.entity.Movie
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class TransformExtensionTest {

    private lateinit var movie: Movie
    private lateinit var movieEntity: MovieEntity
    private lateinit var movieEntityList: List<MovieEntity>

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        movie = Movie(ID, TITLE, POSTER, OVERVIEW, RELEASE_DATE)
        movieEntity = MovieEntity(ID, TITLE, POSTER, OVERVIEW, RELEASE_DATE)
        movieEntityList = listOf(MovieEntity(ID, TITLE, POSTER, OVERVIEW, RELEASE_DATE))
    }

    @Test
    fun `transform a Movie to a MovieDB Entity`() {
        movie.toMovieDB()

        assertEquals(ID, movie.id)
        assertEquals(TITLE, movie.title)
        assertEquals(POSTER, movie.poster)
        assertEquals(OVERVIEW, movie.overview)
        assertEquals(RELEASE_DATE, movie.releaseDate)
    }

    @Test
    fun `transform a Movie Entity list to a MovieDB entity list`() {
        val dbListToMovieList = movieEntityList.toMovieList()

        assertEquals(ID, dbListToMovieList[0].id)
        assertEquals(TITLE, dbListToMovieList[0].title)
        assertEquals(POSTER, dbListToMovieList[0].poster)
        assertEquals(OVERVIEW, dbListToMovieList[0].overview)
        assertEquals(RELEASE_DATE, dbListToMovieList[0].releaseDate)
    }

    @Test
    fun `transform a MovieDB entity to a MovieEntity`() {
        val dbToMovie = movieEntity.toMovie()

        assertEquals(ID, dbToMovie.id)
        assertEquals(TITLE, dbToMovie.title)
        assertEquals(POSTER, dbToMovie.poster)
        assertEquals(OVERVIEW, dbToMovie.overview)
        assertEquals(RELEASE_DATE, dbToMovie.releaseDate)
    }

    companion object {
        const val ID = 123214
        const val TITLE = "Super Mario Bros the Movie"
        const val POSTER = "/1E5baAaEse26fej7uHcjOgEE2t2.jpg"
        const val OVERVIEW = "Mario and Luigi"
        const val RELEASE_DATE = "2023-04-05"
    }
}
