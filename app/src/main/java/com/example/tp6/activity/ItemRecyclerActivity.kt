package com.example.tp6.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.tp6.adapter.MovieAdapter
import com.example.tp6.database.MovieDataBaseImpl
import com.example.tp6.database.MovieRoomDataBase
import com.example.tp6.databinding.ActivityItemRecyclerBinding
import com.example.tp6.mvvm.contract.MainContract
import com.example.tp6.mvvm.model.MainModel
import com.example.tp6.mvvm.viewmodel.MainViewModel
import com.example.tp6.mvvm.viewmodel.factory.ViewModelFactory
import com.example.tp6.service.MovieClient
import com.example.tp6.service.MovieRequestGenerator
import com.example.tp6.service.MovieServiceImpl

class ItemRecyclerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemRecyclerBinding
    private lateinit var viewModel: MainContract.ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityItemRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dataBase: MovieRoomDataBase by lazy {
            Room
                .databaseBuilder(this, MovieRoomDataBase::class.java, "Movie-DataBase")
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
                binding.recycler.layoutManager = LinearLayoutManager(this)
                binding.recycler.adapter = MovieAdapter(data.movies)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.callService()
    }
}
