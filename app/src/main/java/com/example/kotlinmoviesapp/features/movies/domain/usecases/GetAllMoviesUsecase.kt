package com.example.kotlinmoviesapp.features.movies.domain.usecases

import com.example.kotlinmoviesapp.features.movies.domain.repo.MoviesRepo
import javax.inject.Inject

class GetAllMoviesUsecase @Inject constructor(
    private val moviesRepository: MoviesRepo
) {
    suspend fun invoke(category: String) = moviesRepository.getAllMovies(category)
}