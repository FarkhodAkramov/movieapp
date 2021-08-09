package com.example.pandamovie.retrofit

import com.example.pandamovie.models.Movie
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("popular")
  suspend  fun getMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("page") page: Int,
        ):Movie
}