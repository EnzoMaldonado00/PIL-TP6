package com.example.tp6.presentation.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tp6.domain.entity.Movie
import com.example.tp6.presentation.mvvm.model.MainModel
import com.example.tp6.util.CoroutineResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val model: MainModel) : ViewModel() {

    private val mutableLiveData: MutableLiveData<MainData> = MutableLiveData()

    fun getValue(): LiveData<MainData> {
        return mutableLiveData
    }

    fun callService() = viewModelScope.launch {
        withContext(Dispatchers.IO) { model.getMovies() }.let { result ->
            when (result) {
                is CoroutineResult.Success -> {
                    mutableLiveData.value = MainData(MainStatus.SHOW_INFO, result.data)
                }
                is CoroutineResult.Failure -> {
                    mutableLiveData.value = MainData(MainStatus.ERROR, emptyList(), result.exception)
                }
            }
        }
    }

    data class MainData(
        val status: MainStatus,
        val movies: List<Movie>,
        val exception: Exception? = null,
    )

    enum class MainStatus {
        SHOW_INFO,
        ERROR,
    }
}
