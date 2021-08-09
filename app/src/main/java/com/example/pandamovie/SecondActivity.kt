package com.example.pandamovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pandamovie.databinding.ActivitySecondBinding
import com.squareup.picasso.Picasso

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra("name")
        val rate = intent.getDoubleExtra("rate", 0.0)
        val photo = intent.getStringExtra("photo")
        val data = intent.getStringExtra("data")
        val overview = intent.getStringExtra("overview")
        Picasso.get()
            .load("https://image.tmdb.org/t/p/original${photo}")
            .into(binding.imageview)
        binding.nameTv.text = name
        binding.textView.text = "Data\n $data"
        binding.overview.text = overview
    }
}