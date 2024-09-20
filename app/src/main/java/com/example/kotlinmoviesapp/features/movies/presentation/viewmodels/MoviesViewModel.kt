package com.example.kotlinmoviesapp.features.movies.presentation.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinmoviesapp.core.constants.TOP_RATED_KEY
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity
import com.example.kotlinmoviesapp.features.movies.domain.repo.MoviesRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val moviesRepo: MoviesRepo
) : ViewModel() {

    private val _topRatedMovies = MutableLiveData<List<MovieEntity>>()
    val topRatedMovies: LiveData<List<MovieEntity>> = _topRatedMovies

    private val _nowPlayingMovies = MutableLiveData<List<MovieEntity>>()
    val nowPlayingMovies: LiveData<List<MovieEntity>> = _nowPlayingMovies

    fun getAllMovies(category: String) {
        viewModelScope.launch {
            try {
                val liveMovies = moviesRepo.getAllMovies(category)
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
}
