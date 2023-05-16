package com.example.tp6.presentation

import android.app.Application
import com.example.tp6.di.ApiModule.apiModule
import com.example.tp6.di.DBModule.dbModule
import com.example.tp6.di.RepositoryModule.repositoryModule
import com.example.tp6.di.ServiceModule.serviceModule
import com.example.tp6.di.UseCaseModule.useCaseModule
import com.example.tp6.presentation.di.ModelModule.modelModule
import com.example.tp6.presentation.di.ViewModelModule.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class MovieApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MovieApplication)

            modules(
                listOf(
                    viewModelModule,
                    serviceModule,
                    modelModule,
                    useCaseModule,
                    apiModule,
                    dbModule,
                    repositoryModule,
                ),
            )
        }
    }
}
