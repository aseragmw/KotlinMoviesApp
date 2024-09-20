package com.example.kotlinmoviesapp.features.movies.data.datasources.remote

import android.util.Log
import com.example.kotlinmoviesapp.core.api.RetrofitBuilder
import com.example.kotlinmoviesapp.core.api.responses.NowPlayingMoviesResponse
import com.example.kotlinmoviesapp.core.api.responses.TopRatedMoviesResponse
import com.example.kotlinmoviesapp.core.constants.API_TOKEN

class MoviesRemoteDataSource {
    suspend fun getTopRatedMovies(): TopRatedMoviesResponse? {
        try {
            return RetrofitBuilder().ApiService.getTopRatedMovies("Bearer $API_TOKEN")
        }
        catch (e: Exception) {
            Log.d("MoviesRemoteDataSource", "getTopRatedMovies: ${e.message}")
            return null;
        }
    }
    suspend fun getNowPlayingMovies(): NowPlayingMoviesResponse? {
        try {
            return RetrofitBuilder().ApiService.getNowPlayingMovies("Bearer $API_TOKEN")
        }
        catch (e: Exception) {
            Log.d("MoviesRemoteDataSource", "getNowPlayingMovies: ${e.message}")
            return null;
        }
    }
}