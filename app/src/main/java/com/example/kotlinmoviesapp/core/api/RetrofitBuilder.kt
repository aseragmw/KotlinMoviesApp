package com.example.kotlinmoviesapp.core.api

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder (val context : Context){
        private val BASE_URL = "https://api.themoviedb.org/3/movie/"
        fun getInstance(): Retrofit {
            val client = OkHttpClient.Builder()
                .addInterceptor(ChuckerInterceptor(context))
                .build()
            return Retrofit.Builder().client(client).baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
        }
    val ApiService = getInstance().create(MoviesApiService::class.java)
}