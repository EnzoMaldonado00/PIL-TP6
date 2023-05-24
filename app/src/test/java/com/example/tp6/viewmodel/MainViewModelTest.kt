package com.example.tp6.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.tp6.presentation.mvvm.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        mainViewModel = MainViewModel()
    }

    @Test
    fun `set status value as SHOW_MOVIES_LIST`() {
        mainViewModel.onButtonPressed()
        assertEquals(MainViewModel.MainStatus.SHOW_MOVIES_LIST, mainViewModel.getValue().value?.status)
    }

    @Test
    fun `set status value as SHOW_ERROR_DIALOG`() {
        mainViewModel.onShowErrorDialogButtonPressed()
        assertEquals(MainViewModel.MainStatus.SHOW_ERROR_DIALOG, mainViewModel.getValue().value?.status)
    }
}
