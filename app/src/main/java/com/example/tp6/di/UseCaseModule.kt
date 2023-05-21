package com.example.tp6.di

import com.example.tp6.domain.usecase.GetMoviesUseCase
import com.example.tp6.domain.usecase.GetMoviesUseCaseImpl
import org.koin.dsl.module

object UseCaseModule {
    val useCaseModule = module {
        factory<GetMoviesUseCase> { GetMoviesUseCaseImpl(get(), get()) }
    }
}
