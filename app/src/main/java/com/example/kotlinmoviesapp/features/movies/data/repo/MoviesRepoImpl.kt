package com.example.kotlinmoviesapp.features.movies.data.repo

import androidx.lifecycle.LiveData
import com.example.kotlinmoviesapp.core.constants.NOW_PLAYING_KEY
import com.example.kotlinmoviesapp.core.constants.TOP_RATED_KEY
import com.example.kotlinmoviesapp.features.movies.data.datasources.local.MoviesLocalDataSource
import com.example.kotlinmoviesapp.features.movies.data.datasources.remote.MoviesRemoteDataSource
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity
import com.example.kotlinmoviesapp.features.movies.domain.repo.MoviesRepo
import javax.inject.Inject

class MoviesRepoImpl @Inject constructor(
    private val moviesRemoteDataSource: MoviesRemoteDataSource,
    private val moviesLocalDataSource: MoviesLocalDataSource
) : MoviesRepo() {
    override suspend fun getAllMovies(category: String): LiveData<List<MovieEntity>> =
        moviesLocalDataSource.getAllMovies(category)

    override suspend fun updateAllMovies(category: String) {
        val favourites = moviesLocalDataSource.getAllFavoritesAsList()
        when (category) {
            TOP_RATED_KEY -> {
                val movies = moviesRemoteDataSource.getTopRatedMovies()?.results?.map {
                    it.toMovieEntity().copy(category = TOP_RATED_KEY)
                }
                moviesLocalDataSource.deleteAllMovies(category)
                moviesLocalDataSource.insertAllMovies(movies!!)
            }

            NOW_PLAYING_KEY -> {
                val movies = moviesRemoteDataSource.getNowPlayingMovies()?.results?.map {
                    it.toMovieEntity().copy(category = NOW_PLAYING_KEY)
                }
                moviesLocalDataSource.deleteAllMovies(category)
                moviesLocalDataSource.insertAllMovies(movies!!)
            }
        }
        favourites.forEach {
            moviesLocalDataSource.updateMovie(it)
        }
    }

    override fun getMovieById(id: Int): LiveData<MovieEntity> =
        moviesLocalDataSource.getMovieById(id)

    override suspend fun updateMovie(movie: MovieEntity) = moviesLocalDataSource.updateMovie(movie)
    override fun getAllFavorites(): LiveData<List<MovieEntity>> =
        moviesLocalDataSource.getAllFavorites()


}