package com.example.tp6.di

import com.example.tp6.data.database.MovieDataBaseImpl
import com.example.tp6.domain.database.MovieDataBase
import org.koin.dsl.module

object RepositoryModule {
    val repositoryModule = module {
        factory<MovieDataBase> { MovieDataBaseImpl(get()) }
    }
}
