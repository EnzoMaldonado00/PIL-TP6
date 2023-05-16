package com.example.tp6.di

import com.example.tp6.data.service.MovieServiceImpl
import com.example.tp6.domain.service.MovieService
import org.koin.dsl.module

object ServiceModule {
    val serviceModule = module {
        factory<MovieService> { MovieServiceImpl(get()) }
    }
}
