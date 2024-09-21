package com.example.kotlinmoviesapp.features.movies.presentation.screens.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kotlinmoviesapp.core.constants.NOW_PLAYING_KEY
import com.example.kotlinmoviesapp.core.constants.TOP_RATED_KEY
import com.example.kotlinmoviesapp.core.utils.MAIN_BG_COLOR
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity
import com.example.kotlinmoviesapp.features.movies.presentation.screens.home.composables.MovieCard
import com.example.kotlinmoviesapp.features.movies.presentation.viewmodels.MoviesViewModel

@Composable
fun HomeScreen(viewModel: MoviesViewModel,navController: NavController) {
    viewModel.getAllMovies(TOP_RATED_KEY)
    viewModel.getAllMovies(NOW_PLAYING_KEY)

    val topRatedMovies = viewModel.topRatedMovies.observeAsState()
    val nowPlayingMovies = viewModel.nowPlayingMovies.observeAsState()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MAIN_BG_COLOR)
        ) {
            Text(
                text = "Top Rated Movies",
                color = Color.White
            )

            topRatedMovies.value?.let { movieList ->
                if (movieList.isEmpty()) {
                    Text(
                        text = "No movies available",
                        color = Color.White
                    )
                } else {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(movieList.size) { index ->
                            MovieCard(viewModel,movie = movieList[index],navController)
                        }
                    }
                }
            } ?: run {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "Loading...",
                        color = Color.White
                    )
                }
            }
        }
    }
}

