package com.example.tp6.presentation.di

import com.example.tp6.presentation.mvvm.model.MainModel
import org.koin.dsl.module

object ModelModule {
    val modelModule = module {
        factory { MainModel(get()) }
    }
}