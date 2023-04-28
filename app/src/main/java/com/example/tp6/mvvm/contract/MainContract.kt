package com.example.tp6.mvvm.contract

import androidx.lifecycle.LiveData
import com.example.tp6.mvvm.viewmodel.MainViewModel
import com.example.tp6.service.model.Movie
import com.example.tp6.util.CoroutineResult
import kotlinx.coroutines.Job

interface MainContract {
    interface Model {
        suspend fun getMovies(): CoroutineResult<List<Movie>>
    }

    interface ViewModel {
        fun getValue(): LiveData<MainViewModel.MainData>
        fun callService(): Job
    }
}
