package com.example.kotlinmoviesapp.features.movies.domain.usecases

import com.example.kotlinmoviesapp.features.movies.domain.repo.MoviesRepo
import javax.inject.Inject

class UpdateAllMoviesUsecase @Inject constructor(
    private val moviesRepository: MoviesRepo
) {
    suspend fun invoke(category: String) = moviesRepository.updateAllMovies(category)
}
