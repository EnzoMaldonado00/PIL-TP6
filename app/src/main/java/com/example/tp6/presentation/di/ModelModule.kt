package com.example.tp6.presentation.di

import com.example.tp6.presentation.mvvm.model.MovieModel
import org.koin.dsl.module

object ModelModule {
    val modelModule = module {
        factory { MovieModel(get()) }
    }
}
