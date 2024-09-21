package com.example.kotlinmoviesapp.features.movies.presentation.screens.movie_details.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.kotlinmoviesapp.R
import com.example.kotlinmoviesapp.core.utils.MAIN_BG_COLOR
import com.example.kotlinmoviesapp.core.utils.TEXT_GREY_COLOR
import com.example.kotlinmoviesapp.core.utils.WHITE_COLOR
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity
import com.example.kotlinmoviesapp.features.movies.presentation.viewmodels.MoviesViewModel

@Composable
fun MovieDetailsScreen(viewModel: MoviesViewModel) {
    val selectedMovie = viewModel.selectedMovie.observeAsState()
    Scaffold { paddding ->
        selectedMovie.value?.let { movie ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddding)
                    .background(MAIN_BG_COLOR)
            ) {
                LazyRow (
                    modifier = Modifier.fillMaxWidth()
                        .fillMaxHeight(.25f),
                ){
                    item {
                        AsyncImage(
                            model = movie.posterPath,
                            contentDescription = movie.title
                        )
                    }
                    item {
                        AsyncImage(
                            model = movie.backdropPath,
                            contentDescription = movie.title
                        )
                    }
                }

                Text(
                    text = movie.title ?: "",
                    fontSize = 30.sp,
                    color = WHITE_COLOR
                )
                Row {
                    Icon(
                        painter = painterResource(R.drawable.baseline_star_rate_24),
                        contentDescription = "Rating",
                        tint = TEXT_GREY_COLOR
                    )
                    Text(
                        text = movie.voteAverage.toString(),
                        color = TEXT_GREY_COLOR,
                        fontSize = 16.sp
                    )
                }
                HorizontalDivider(color = TEXT_GREY_COLOR)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Release Date", color = WHITE_COLOR)
                        Text(movie.releaseDate ?: "", color = TEXT_GREY_COLOR)
                    }
                    Column(
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Adult Only", color = WHITE_COLOR)
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .clip(RoundedCornerShape(30.dp))
                                .background(if (movie.adult) Color.Red else Color.Green)
                        ) {
                            Text(if (movie.adult) "Yes" else "No", color = TEXT_GREY_COLOR)
                        }
                    }
                }
                HorizontalDivider(color = TEXT_GREY_COLOR)
                Text("Overview", color = WHITE_COLOR)
                Text(movie.overview, color = TEXT_GREY_COLOR)
            }
        }
    }
}