package com.example.kotlinmoviesapp.features.movies.presentation.screens.home.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.kotlinmoviesapp.core.constants.NOW_PLAYING_KEY
import com.example.kotlinmoviesapp.core.constants.TOP_RATED_KEY
import com.example.kotlinmoviesapp.core.utils.MAIN_BG_COLOR
import com.example.kotlinmoviesapp.core.utils.TEXT_GREY_COLOR
import com.example.kotlinmoviesapp.core.utils.WHITE_COLOR
import com.example.kotlinmoviesapp.features.movies.presentation.screens.home.composables.MovieCard
import com.example.kotlinmoviesapp.features.movies.presentation.viewmodels.MoviesViewModel

@Composable
fun HomeScreen(viewModel: MoviesViewModel, navController: NavController) {
    var selectedCategory by remember { mutableStateOf(TOP_RATED_KEY) }
    val movies = if (selectedCategory == TOP_RATED_KEY) {
        viewModel.getAllMovies(TOP_RATED_KEY)
        viewModel.topRatedMovies.observeAsState()
    } else {
        viewModel.getAllMovies(NOW_PLAYING_KEY)
        viewModel.nowPlayingMovies.observeAsState()
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MAIN_BG_COLOR)
        ) {

            Box(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(TOP_RATED_KEY, modifier = Modifier.clickable {
                    selectedCategory = TOP_RATED_KEY

                }, fontWeight = FontWeight.SemiBold, fontSize = if(selectedCategory==TOP_RATED_KEY) 20.sp else 17.sp, color = if(selectedCategory==TOP_RATED_KEY) WHITE_COLOR else TEXT_GREY_COLOR)
                Text(NOW_PLAYING_KEY, modifier = Modifier.clickable {
                    selectedCategory = NOW_PLAYING_KEY

                }, fontWeight = FontWeight.SemiBold, fontSize = if(selectedCategory== NOW_PLAYING_KEY) 20.sp else 17.sp, color = if(selectedCategory== NOW_PLAYING_KEY) WHITE_COLOR else TEXT_GREY_COLOR)

            }
            Box(modifier = Modifier.height(20.dp))
            movies.value?.let { movieList ->
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
                            MovieCard(viewModel, movie = movieList[index], navController)
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

