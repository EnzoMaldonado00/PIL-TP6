package com.example.tp6.di

import com.example.tp6.data.service.MovieRequestGenerator
import org.koin.dsl.module

object ApiModule {
    val apiModule = module {
        factory { MovieRequestGenerator }
    }
}
