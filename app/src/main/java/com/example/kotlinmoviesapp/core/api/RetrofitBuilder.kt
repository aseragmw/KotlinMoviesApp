package com.example.kotlinmoviesapp.core.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
        private val BASE_URL = "https://api.themoviedb.org/3/movie/"
        fun getInstance(): Retrofit {
            return Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
    val ApiService = getInstance().create(MoviesApiService::class.java)
}