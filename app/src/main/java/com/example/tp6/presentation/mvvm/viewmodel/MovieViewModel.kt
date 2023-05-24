package com.example.tp6.presentation.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp6.domain.entity.Movie
import com.example.tp6.domain.util.CoroutineResult
import com.example.tp6.presentation.mvvm.model.MovieModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel(private val model: MovieModel) : ViewModel() {

    private val mutableLiveData: MutableLiveData<MovieData> = MutableLiveData()

    fun getValue(): LiveData<MovieData> {
        return mutableLiveData
    }

    fun callService() = viewModelScope.launch {
        withContext(Dispatchers.IO) { model.getMovies() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableLiveData.value = MovieData(MovieStatus.SHOW_INFO, result.data)
                }
                is CoroutineResult.Failure -> {
                    mutableLiveData.value = MovieData(MovieStatus.ERROR, emptyList(), result.exception)
                }
            }
        }
    }

    data class MovieData(
        val status: MovieStatus,
        val movies: List<Movie>,
        val exception: Exception? = null,
    )

    enum class MovieStatus {
        SHOW_INFO,
        ERROR,
    }
}
