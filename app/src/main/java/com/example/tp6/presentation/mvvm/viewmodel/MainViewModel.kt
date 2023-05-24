package com.example.tp6.presentation.mvvm.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val mutableLiveData: MutableLiveData<MainData> = MutableLiveData()

    fun getValue(): LiveData<MainData> {
        return mutableLiveData
    }

    fun onButtonPressed() {
        mutableLiveData.value = MainData(MainStatus.SHOW_MOVIES_LIST)
    }

    fun onShowErrorDialogButtonPressed() {
        mutableLiveData.value = MainData(MainStatus.SHOW_ERROR_DIALOG)
    }

    data class MainData(
        val status: MainStatus,
    )

    enum class MainStatus {
        SHOW_MOVIES_LIST,
        SHOW_ERROR_DIALOG,
    }
}
