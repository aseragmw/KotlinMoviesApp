package com.example.kotlinmoviesapp.core.api

import com.example.kotlinmoviesapp.core.api.responses.NowPlayingMoviesResponse
import com.example.kotlinmoviesapp.core.api.responses.TopRatedMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface MoviesApiService {
    @GET("top_rated?language=en-US&page=1")
    suspend fun getTopRatedMovies(
        @Header("Authorization") token: String
    ): TopRatedMoviesResponse

    @GET("now_playing?language=en-US&page=1")
    suspend fun getNowPlayingMovies(
        @Header("Authorization") token: String
    ): NowPlayingMoviesResponse
}

