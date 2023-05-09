package com.example.tp6.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.tp6.R
import com.example.tp6.adapter.MovieAdapter
import com.example.tp6.database.MovieDataBaseImpl
import com.example.tp6.database.MovieRoomDataBase
import com.example.tp6.databinding.ActivityMovieListBinding
import com.example.tp6.mvvm.contract.MainContract
import com.example.tp6.mvvm.model.MainModel
import com.example.tp6.mvvm.viewmodel.MainViewModel
import com.example.tp6.mvvm.viewmodel.factory.ViewModelFactory
import com.example.tp6.service.MovieClient
import com.example.tp6.service.MovieRequestGenerator
import com.example.tp6.service.MovieServiceImpl
import com.example.tp6.util.ErrorDialog

class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding
    private lateinit var viewModel: MainContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataBase: MovieRoomDataBase by lazy {
            Room
                .databaseBuilder(this, MovieRoomDataBase::class.java, "Movie-DataBase")
                .fallbackToDestructiveMigration()
                .build()
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                arrayOf(
                    MainModel(
                        MovieServiceImpl(MovieRequestGenerator.createService(MovieClient::class.java)),
                        MovieDataBaseImpl(dataBase.movieDao()),
                    ),
                ),
            ),
        )[MainViewModel::class.java]

        viewModel.getValue().observe(this) { updateUI(it) }
    }

    private fun updateUI(data: MainViewModel.MainData) {
        when (data.status) {
            MainViewModel.MainStatus.SHOW_INFO -> {
                if (data.movies.isEmpty()) {
                    ErrorDialog.showErrorDialog(this, R.string.network_error_message)
                    binding.recycler.visibility = RecyclerView.GONE
                    binding.listTitle.visibility = RecyclerView.GONE
                    binding.emptyState.visibility = RecyclerView.VISIBLE
                } else {
                    binding.recycler.layoutManager = LinearLayoutManager(this)
                    binding.recycler.adapter = MovieAdapter(data.movies)
                }
            }
            MainViewModel.MainStatus.ERROR -> {
                ErrorDialog.showErrorDialog(this, R.string.error_dialog_message)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
    }
}
