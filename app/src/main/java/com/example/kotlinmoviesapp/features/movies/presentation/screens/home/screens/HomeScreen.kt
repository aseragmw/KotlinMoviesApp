package com.example.kotlinmoviesapp.features.movies.presentation.screens.home.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kotlinmoviesapp.R
import com.example.kotlinmoviesapp.core.constants.FAVOURITES_KEY
import com.example.kotlinmoviesapp.core.constants.NOW_PLAYING_KEY
import com.example.kotlinmoviesapp.core.constants.TOP_RATED_KEY
import com.example.kotlinmoviesapp.core.utils.MAIN_BG_COLOR
import com.example.kotlinmoviesapp.core.utils.RED_COLOR
import com.example.kotlinmoviesapp.core.utils.TEXT_GREY_COLOR
import com.example.kotlinmoviesapp.core.utils.WHITE_COLOR
import com.example.kotlinmoviesapp.features.movies.presentation.screens.home.composables.MovieCard
import com.example.kotlinmoviesapp.features.movies.presentation.viewmodels.MoviesViewModel
import com.valentinilk.shimmer.shimmer

@SuppressLint("SuspiciousIndentation")
@Composable
fun HomeScreen(viewModel: MoviesViewModel, navController: NavController) {
    var selectedCategory by remember { mutableStateOf(TOP_RATED_KEY) }
    val movies
            = when (selectedCategory) {
        TOP_RATED_KEY -> {
            viewModel.topRatedMovies.observeAsState()
        }

        NOW_PLAYING_KEY -> {
            viewModel.nowPlayingMovies.observeAsState()
        }

        else -> {
            viewModel.favoritesMovies.observeAsState()
        }
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
                Text(
                    TOP_RATED_KEY,
                    modifier = Modifier.clickable {
                        selectedCategory = TOP_RATED_KEY

                    },
                    fontWeight = FontWeight.SemiBold,
                    fontSize = if (selectedCategory == TOP_RATED_KEY) 20.sp else 17.sp,
                    color = if (selectedCategory == TOP_RATED_KEY) WHITE_COLOR else TEXT_GREY_COLOR
                )
                Text(
                    NOW_PLAYING_KEY,
                    modifier = Modifier.clickable {
                        selectedCategory = NOW_PLAYING_KEY

                    },
                    fontWeight = FontWeight.SemiBold,
                    fontSize = if (selectedCategory == NOW_PLAYING_KEY) 20.sp else 17.sp,
                    color = if (selectedCategory == NOW_PLAYING_KEY) WHITE_COLOR else TEXT_GREY_COLOR
                )
                Text(
                    FAVOURITES_KEY,
                    modifier = Modifier.clickable {
                        selectedCategory = FAVOURITES_KEY

                    },
                    fontWeight = FontWeight.SemiBold,
                    fontSize = if (selectedCategory == FAVOURITES_KEY) 20.sp else 17.sp,
                    color = if (selectedCategory == FAVOURITES_KEY) WHITE_COLOR else TEXT_GREY_COLOR
                )

            }
            if(movies.value==null){
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize()
                ){
                    items(10){
                        Column(
                            modifier = Modifier
                                .shimmer()
                                .fillMaxWidth(0.5f)
                                .padding(horizontal = 5.dp)
                                .fillMaxHeight()
                                .fillMaxWidth(1f)
                                .padding(vertical = 10.dp)
                                .background(TEXT_GREY_COLOR)
                        ) {
                            Box {
                                Box(
                                    modifier = Modifier.height(200.dp)
                                        .fillMaxWidth(1f)
                                        .padding(5.dp)
                                )

                            }
                            Box(modifier= Modifier.height(10.dp))
                            Text(
                                text = "",
                                color = WHITE_COLOR,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                            Box(modifier= Modifier.height(5.dp))
                            Text(
                                text = "",
                                color = TEXT_GREY_COLOR,
                                fontSize = 20.sp
                            )
                            Box(modifier= Modifier.height(10.dp))
                        }
                    }
                }
            }
            Box(modifier = Modifier.height(20.dp))
            movies.value?.let { movieList ->
                if (movieList.isNotEmpty()) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(movieList.size) { index ->

                            MovieCard(viewModel, movie = movieList[index], navController)
                        }
                    }
                } else {
                    if(selectedCategory == FAVOURITES_KEY)
                        Column(modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(
                                text = "No Favourites Yet",
                                color = Color.White,
                                fontSize = 30.sp,
                            )
                            Box(modifier = Modifier.height(30.dp))
                            Image(
                                painter = painterResource(R.drawable.not_found),
                                contentDescription = "not found",
                                modifier = Modifier.height(200.dp)
                                    .width(200.dp)
                                    . clip(RoundedCornerShape(50.dp)),
                            )
                        }
                }
            }
        }
    }
}

