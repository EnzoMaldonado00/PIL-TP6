package com.example.tp6.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp6.R
import com.example.tp6.databinding.ActivityMovieListBinding
import com.example.tp6.presentation.adapter.MovieAdapter
import com.example.tp6.presentation.mvvm.viewmodel.MainViewModel
import com.example.tp6.util.ErrorDialogFragment
import org.koin.android.ext.android.inject

class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding
    private val viewModel: MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.callService()
        viewModel.getValue().observe(this) { updateUI(it) }
    }

    private fun updateUI(data: MainViewModel.MainData) {
        when (data.status) {
            MainViewModel.MainStatus.SHOW_INFO -> {
                if (data.movies.isEmpty()) {
                    showDialog(getString(R.string.network_error_message))
                    binding.recycler.visibility = RecyclerView.GONE
                    binding.listTitle.visibility = RecyclerView.GONE
                    binding.emptyState.visibility = RecyclerView.VISIBLE
                } else {
                    binding.recycler.layoutManager = LinearLayoutManager(this)
                    binding.recycler.adapter = MovieAdapter(data.movies)
                }
            }
            MainViewModel.MainStatus.ERROR -> {
                showDialog(getString(R.string.error_dialog_message))
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
    }

    private fun showDialog(message: String) {
        val dialogFragment = ErrorDialogFragment.newInstance(message)
        dialogFragment.show(supportFragmentManager, "dialog")
    }
}
