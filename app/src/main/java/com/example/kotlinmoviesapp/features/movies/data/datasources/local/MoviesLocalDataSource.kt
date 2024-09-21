package com.example.kotlinmoviesapp.features.movies.data.datasources.local

import com.example.kotlinmoviesapp.core.db.MoviesDAO
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity
import javax.inject.Inject

class MoviesLocalDataSource @Inject constructor(
    private val moviesDao: MoviesDAO
) {
    suspend fun insertAllMovies(movies:List<MovieEntity>) = moviesDao.insertAllMovies(movies)
    fun getAllMovies(category:String) = moviesDao.getAllMovies(category)
    suspend fun deleteAllMovies(category: String) = moviesDao.deleteAllMovies(category)
    fun getMovieById(id:Int) = moviesDao.getMovieById(id)
    suspend fun updateMovie(movie:MovieEntity) = moviesDao.updateMovie(movie)
    fun getAllFavorites() = moviesDao.getAllFavorites()
}