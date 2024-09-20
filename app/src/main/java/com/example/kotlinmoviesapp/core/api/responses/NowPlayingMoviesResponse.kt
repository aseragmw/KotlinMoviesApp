package com.example.kotlinmoviesapp.core.api.responses

import com.example.kotlinmoviesapp.features.movies.data.models.MovieModel

data class NowPlayingMoviesResponse(
    val dates:Map<String,String>?,
    val page:Int?,
    val results:List<MovieModel>?,
    val total_pages:Int?,
    val total_results:Int?
)