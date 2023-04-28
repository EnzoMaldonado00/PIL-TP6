package com.example.tp6.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp6.database.dao.MovieDao
import com.example.tp6.database.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 1,
)
abstract class MovieRoomDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
