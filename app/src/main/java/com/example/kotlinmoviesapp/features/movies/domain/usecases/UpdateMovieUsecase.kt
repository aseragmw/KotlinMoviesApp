package com.example.kotlinmoviesapp.features.movies.domain.usecases

import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity
import com.example.kotlinmoviesapp.features.movies.domain.repo.MoviesRepo
import javax.inject.Inject

class UpdateMovieUsecase @Inject constructor(
    private val moviesRepository: MoviesRepo
) {
    suspend fun invoke(movie:MovieEntity) = moviesRepository.updateMovie(movie)
}
