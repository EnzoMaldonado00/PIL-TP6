package com.example.tp6.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp6.data.entity.MovieEntity

@Database(
    entities = [
        MovieEntity::class,
    ],
    version = 2,
)
abstract class MovieRoomDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}
