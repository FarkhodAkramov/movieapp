package com.example.pandamovie.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.pandamovie.paging.MovieDataSource
import com.example.pandamovie.retrofit.ApiService

class MoviesViewModel(val apiService: ApiService) : ViewModel() {
    val movie = Pager(PagingConfig(80)) {
        (MovieDataSource(apiService))
    }.flow.cachedIn(viewModelScope)
}