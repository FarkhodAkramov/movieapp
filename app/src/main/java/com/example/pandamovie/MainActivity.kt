package com.example.pandamovie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import com.example.pandamovie.adapters.MoviesPagingAdapter
import com.example.pandamovie.databinding.ActivityMainBinding
import com.example.pandamovie.models.Result
import com.example.pandamovie.retrofit.ApiClient
import com.example.pandamovie.viewmodel.MoviesViewModel
import com.example.pandamovie.viewmodel.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    lateinit var moviesPagingAdapter: MoviesPagingAdapter
    private lateinit var moviesViewModel: MoviesViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = "Movies"

        moviesViewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiClient.apiService)
        )[MoviesViewModel::class.java]
        moviesPagingAdapter = MoviesPagingAdapter(object : MoviesPagingAdapter.OnClickListener {
            override fun OnItemClick(result: Result) {
                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("photo", result.backdrop_path)
                intent.putExtra("overview",result.overview)
                intent.putExtra("data",result.release_date)
                intent.putExtra("rate",result.vote_average)
                intent.putExtra("name",result.original_title)
                startActivity(intent)
            }


        })
        binding.rv.hasFixedSize()
        binding.rv.adapter = moviesPagingAdapter
        lifecycleScope.launch {
            moviesViewModel.movie.collectLatest {
                moviesPagingAdapter.submitData(it)
            }
        }

    }

}