package com.example.kotlinmoviesapp.features.movies.presentation.screens.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity
import com.example.kotlinmoviesapp.features.movies.presentation.viewmodels.MoviesViewModel
import com.google.gson.Gson

@Composable
fun MovieCard(viewModel: MoviesViewModel,movie: MovieEntity, navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .fillMaxHeight(0.33f)
            .clickable {
               viewModel.getMovieById(movie.id)
                navController.navigate("details")
            }

    ){
        Box (
            modifier = Modifier.fillMaxHeight()
        ){
            AsyncImage(
                model = movie.posterPath,
                contentDescription = movie.title,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = movie.title,
            color = Color.White
        )
    }
}