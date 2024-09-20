package com.example.kotlinmoviesapp.features.movies.domain.entities

import androidx.room.Entity

@Entity (tableName = "Movies", primaryKeys = ["id","category"])
data class MovieEntity(
    val id: Int,
    val adult: Boolean,
    val backdropPath: String,
    val originalLanguage: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int,
    val category: String
)