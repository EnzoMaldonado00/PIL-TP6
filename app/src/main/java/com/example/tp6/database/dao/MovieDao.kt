package com.example.tp6.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tp6.database.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(binEntity: MovieEntity): Long

    @Query("SELECT * FROM movies")
    fun getDBCharacters(): List<MovieEntity>
}
