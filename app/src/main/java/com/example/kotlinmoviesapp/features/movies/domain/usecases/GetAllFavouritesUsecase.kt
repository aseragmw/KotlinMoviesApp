package com.example.kotlinmoviesapp.features.movies.domain.usecases

import com.example.kotlinmoviesapp.features.movies.domain.repo.MoviesRepo
import javax.inject.Inject

class GetAllFavouritesUsecase @Inject constructor(
    private val moviesRepository: MoviesRepo
) {
    fun invoke() = moviesRepository.getAllFavorites()
}