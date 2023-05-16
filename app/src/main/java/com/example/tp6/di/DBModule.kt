package com.example.tp6.di

import androidx.room.Room
import com.example.tp6.data.database.MovieRoomDataBase
import org.koin.dsl.module

object DBModule {
    private const val DB = "MovieDataBase"

    val dbModule = module {
        single { Room.databaseBuilder(get(), MovieRoomDataBase::class.java, DB).build() }
        single { get<MovieRoomDataBase>().movieDao() }
    }
}
