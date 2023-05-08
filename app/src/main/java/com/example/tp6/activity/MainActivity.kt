package com.example.tp6.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp6.databinding.ActivityMainBinding
import com.example.tp6.util.ErrorDialog

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetInfo.setOnClickListener {
            val intent = Intent(this, MovieListActivity::class.java)
            startActivity(intent)
        }

        binding.title.setOnClickListener {
            errorTrigger()
        }
    }

    private fun errorTrigger(): Int {
        var trigger = 0
        try {
            trigger = 2 / 0
            return trigger
        } catch (e: Exception) {
            ErrorDialog.showErrorDialog(this)
        }
        return trigger
    }
}
