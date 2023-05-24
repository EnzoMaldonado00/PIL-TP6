package com.example.tp6.presentation.di

import com.example.tp6.presentation.mvvm.viewmodel.MainViewModel
import com.example.tp6.presentation.mvvm.viewmodel.MovieViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelModule {
    val viewModelModule = module {
        viewModel { MovieViewModel(get()) }
        viewModel { MainViewModel() }
    }
}
