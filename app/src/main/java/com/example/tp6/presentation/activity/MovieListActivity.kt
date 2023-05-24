package com.example.tp6.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp6.databinding.ActivityMovieListBinding
import com.example.tp6.presentation.adapter.MovieAdapter
import com.example.tp6.presentation.mvvm.viewmodel.MovieViewModel
import org.koin.android.ext.android.inject

class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding
    private val viewModel: MovieViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getValue().observe(this) { updateUI(it) }
        viewModel.callService()
    }

    private fun updateUI(data: MovieViewModel.MovieData) {
        when (data.status) {
            MovieViewModel.MovieStatus.SHOW_INFO -> {
                binding.recycler.layoutManager = LinearLayoutManager(this)
                binding.recycler.adapter = MovieAdapter(data.movies)
            }
            MovieViewModel.MovieStatus.ERROR -> {
                binding.recycler.visibility = RecyclerView.GONE
                binding.listTitle.visibility = RecyclerView.GONE
                binding.emptyState.visibility = RecyclerView.VISIBLE
            }
        }
    }
}
