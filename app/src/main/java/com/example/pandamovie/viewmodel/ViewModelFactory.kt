package com.example.pandamovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pandamovie.retrofit.ApiService

class ViewModelFactory(val apiService: ApiService) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MoviesViewModel::class.java)) {
            return MoviesViewModel(apiService) as T
        }
        throw IllegalAccessException("Error")
    }
}