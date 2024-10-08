package com.example.kotlinmoviesapp.features.movies.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinmoviesapp.core.constants.TOP_RATED_KEY
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity
import com.example.kotlinmoviesapp.features.movies.domain.repo.MoviesRepo
import com.example.kotlinmoviesapp.features.movies.domain.usecases.GetAllFavouritesUsecase
import com.example.kotlinmoviesapp.features.movies.domain.usecases.GetAllMoviesUsecase
import com.example.kotlinmoviesapp.features.movies.domain.usecases.GetMovieByIdUsecase
import com.example.kotlinmoviesapp.features.movies.domain.usecases.UpdateAllMoviesUsecase
import com.example.kotlinmoviesapp.features.movies.domain.usecases.UpdateMovieUsecase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getMoviesUsecase: GetAllMoviesUsecase,
    private val updateMoviesUsecase: UpdateAllMoviesUsecase,
    private val getMovieByIdUsecase: GetMovieByIdUsecase,
    private val updateMovieUsecase: UpdateMovieUsecase,
    private val getAllFavouritesUsecase: GetAllFavouritesUsecase
) : ViewModel() {

    private val _topRatedMovies = MutableLiveData<List<MovieEntity>>()
    val topRatedMovies: LiveData<List<MovieEntity>> = _topRatedMovies

    private val _nowPlayingMovies = MutableLiveData<List<MovieEntity>>()
    val nowPlayingMovies: LiveData<List<MovieEntity>> = _nowPlayingMovies

    private val _favoritesMovies = MutableLiveData<List<MovieEntity>>()
    val favoritesMovies: LiveData<List<MovieEntity>> = _favoritesMovies

    private var _selectedMovie: MutableLiveData<MovieEntity> = MutableLiveData()
    val selectedMovie: LiveData<MovieEntity> = _selectedMovie

    fun getMovieById(id: Int) {
        viewModelScope.launch {
            try {
                val movie = getMovieByIdUsecase.invoke(id)
                movie.observeForever {
                    _selectedMovie.postValue(it)
                }
            } catch (e: Exception) {
                Log.e("MoviesViewModel", "Error fetching movies: ${e.message}")
            }
        }
    }

    fun getAllMovies(category: String) {
        viewModelScope.launch {
            try {
                val liveMovies = getMoviesUsecase.invoke(category)
                liveMovies.observeForever { movieList ->
                    if (category == TOP_RATED_KEY) {
                        _topRatedMovies.postValue(movieList)
                    } else {
                        _nowPlayingMovies.postValue(movieList)
                    }
                }
            } catch (e: Exception) {
                Log.e("MoviesViewModel", "Error fetching movies: ${e.message}")
            }
        }
    }

    fun updateAllMovies(category: String) {
        Log.d("MoviesViewModel", "updateAllMovies: $category")
        viewModelScope.launch {
            try {
                updateMoviesUsecase.invoke(category)
            } catch (e: Exception) {
                Log.e("MoviesViewModel", "Error fetching movies: ${e.message}")
            }
        }
    }

    fun addOrRemoveFavorite(movie: MovieEntity) {
        viewModelScope.launch {
            try {
                updateMovieUsecase.invoke(movie)
            } catch (e: Exception) {
                Log.e("MoviesViewModel", "Error fetching movies: ${e.message}")
            }
        }
    }

    fun getAllFavorites(){
        viewModelScope.launch {
            try{
                val favourites = getAllFavouritesUsecase.invoke()
                favourites.observeForever { movieList ->
                    _favoritesMovies.postValue(movieList)
                }
            }
            catch (e:Exception){
                Log.e("MoviesViewModel", "Error fetching movies: ${e.message}")
            }
        }
    }
}
