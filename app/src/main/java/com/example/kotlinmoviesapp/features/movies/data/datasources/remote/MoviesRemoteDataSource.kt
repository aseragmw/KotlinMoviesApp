package com.example.kotlinmoviesapp.features.movies.data.datasources.remote

import android.content.Context
import android.util.Log
import com.example.kotlinmoviesapp.BuildConfig
import com.example.kotlinmoviesapp.core.api.RetrofitBuilder
import com.example.kotlinmoviesapp.core.api.responses.NowPlayingMoviesResponse
import com.example.kotlinmoviesapp.core.api.responses.TopRatedMoviesResponse
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(val context:Context){
    suspend fun getTopRatedMovies(): TopRatedMoviesResponse? {
        try {
            return RetrofitBuilder(context).ApiService.getTopRatedMovies("Bearer ${BuildConfig.API_KEY}")
        }
        catch (e: Exception) {
            Log.d("MoviesRemoteDataSource", "getTopRatedMovies: ${e.message}")
            return null;
        }
    }
    suspend fun getNowPlayingMovies(): NowPlayingMoviesResponse? {
        try {
            return RetrofitBuilder(context).ApiService.getNowPlayingMovies("Bearer ${BuildConfig.API_KEY}")
        }
        catch (e: Exception) {
            Log.d("MoviesRemoteDataSource", "getNowPlayingMovies: ${e.message}")
            return null;
        }
    }
}