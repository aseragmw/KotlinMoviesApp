package com.example.kotlinmoviesapp.features.movies.domain.repo

import androidx.lifecycle.LiveData
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity

abstract class MoviesRepo {
    abstract suspend fun getAllMovies(category:String): LiveData<List<MovieEntity>>
    abstract suspend fun updateAllMovies(category: String)
    abstract  fun getMovieById(id:Int):LiveData<MovieEntity>
}