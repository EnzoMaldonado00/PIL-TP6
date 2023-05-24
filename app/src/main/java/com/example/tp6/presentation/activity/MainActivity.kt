package com.example.tp6.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp6.R
import com.example.tp6.databinding.ActivityMainBinding
import com.example.tp6.presentation.mvvm.viewmodel.MainViewModel
import com.example.tp6.presentation.util.ErrorDialogFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mainViewModel.getValue().observe(this) { updateUI(it) }
        binding.btnGetInfo.setOnClickListener { mainViewModel.onButtonPressed() }
        binding.btnShowErrorDialog.setOnClickListener { mainViewModel.onShowErrorDialogButtonPressed() }
    }

    private fun updateUI(data: MainViewModel.MainData) {
        when (data.status) {
            MainViewModel.MainStatus.SHOW_MOVIES_LIST -> {
                val intent = Intent(this, MovieListActivity::class.java)
                startActivity(intent)
            }
            MainViewModel.MainStatus.SHOW_ERROR_DIALOG -> {
                showDialog(getString(R.string.error_dialog_message))
            }
        }
    }

    private fun showDialog(message: String) {
        val dialogFragment = ErrorDialogFragment.newInstance(message)
        dialogFragment.show(supportFragmentManager, "dialog")
    }
}
