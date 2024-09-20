package com.example.kotlinmoviesapp.features.movies.data.models

import com.example.kotlinmoviesapp.core.constants.NO_CATEGORY
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity

class MovieModel(
    val adult: Boolean?,
    val backdrop_path: String?,
    val genre_ids: List<Int>?,
    val id: Int,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val release_date: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Double?,
    val vote_count: Int?,
){
    fun toMovieEntity():MovieEntity = MovieEntity(
        id,
        adult?:false,
        "https://image.tmdb.org/t/p/original$backdrop_path"?:"https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Image_not_available.png/800px-Image_not_available.png",
        original_language?:"en",
        overview?:"No Overview Found",
        popularity?:0.0,
        "https://image.tmdb.org/t/p/original$poster_path"?:"https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Image_not_available.png/800px-Image_not_available.png",
        release_date?:"No Date Found",
        title?:"No Title Found",
        vote_average?:0.0,
        vote_count?:0,
        NO_CATEGORY
    )
}