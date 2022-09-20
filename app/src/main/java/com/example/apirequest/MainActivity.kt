package com.example.apirequest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.apirequest.adapter.MovieAdapter
import com.example.apirequest.api.RetrofitService
import com.example.apirequest.databinding.ActivityMainBinding
import com.example.apirequest.repository.MainRepository
import com.example.apirequest.viewModel.MainViewModel
import com.example.apirequest.viewModel.MainViewModelFactory

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    private val adapter = MovieAdapter()
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val retrofitService = RetrofitService.getInstance()
        val mainRepository = MainRepository(retrofitService)
        binding.recyclerview.adapter = adapter

        viewModel = ViewModelProvider(this, MainViewModelFactory(mainRepository)).get(MainViewModel::class.java)

        viewModel.movieList.observe(this){
            adapter.setMovies(it)
        }

        viewModel.errorMessage.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.loading.observe(this, Observer {
            if(it){
                binding.progress.visibility = View.VISIBLE
            }else{
                binding.progress.visibility = View.GONE
            }
        })

        viewModel.getAllMovies()
    }
}