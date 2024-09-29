package com.example.kotlinmoviesapp.features.movies.presentation.screens.movie_details.screens

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.kotlinmoviesapp.R
import com.example.kotlinmoviesapp.core.utils.MAIN_BG_COLOR
import com.example.kotlinmoviesapp.core.utils.RED_COLOR
import com.example.kotlinmoviesapp.core.utils.TEXT_GREY_COLOR
import com.example.kotlinmoviesapp.core.utils.WHITE_COLOR
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity
import com.example.kotlinmoviesapp.features.movies.presentation.viewmodels.MoviesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailsScreen(viewModel: MoviesViewModel, navController: NavController) {
    val selectedMovie = viewModel.selectedMovie.observeAsState()
    var iconColor by remember { mutableStateOf(
        if (selectedMovie.value?.isFavorite == true) RED_COLOR else WHITE_COLOR
    ) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MAIN_BG_COLOR),
        topBar = {
            TopAppBar(
                title = { Text(text = "Movie Details", color = WHITE_COLOR) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back",
                            tint = WHITE_COLOR,
                            modifier = Modifier
                                .size(55.dp)
                                .clickable {
                                    navController.popBackStack()
                                }
                        )
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MAIN_BG_COLOR)
            )
        }
    ) { paddding ->
        selectedMovie.value?.let { movie ->
            Log.d("MovieDetailsScreen", "Movie: ${movie.isFavorite}")
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
                    .background(MAIN_BG_COLOR)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddding)
                        .background(MAIN_BG_COLOR)

                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)

                            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
                    ) {
                        LazyRow(
                            modifier = Modifier.align(Alignment.Center),
                        ) {
                            item {
                                AsyncImage(
                                    model = movie.posterPath,
                                    contentDescription = movie.title,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(end = 5.dp),
                                    contentScale = ContentScale.FillWidth
                                )
                            }
                            item {
                                AsyncImage(
                                    model = movie.backdropPath,
                                    contentDescription = movie.title,
                                    modifier = Modifier.fillMaxWidth(),
                                    contentScale = ContentScale.FillWidth
                                )
                            }
                        }
                        Icon(
                            imageVector = Icons.Default.Favorite,
                            contentDescription = "Favourite",
                            tint = iconColor,
                            modifier = Modifier
                                .padding(10.dp)
                                .background(Color.Transparent)
                                .padding(end = 30.dp)
                                .align(Alignment.BottomEnd)
                                .size(45.dp)
                                .clickable {
                                    movie.isFavorite = !movie.isFavorite
                                    viewModel.addOrRemoveFavorite(movie)
                                    iconColor = if (movie.isFavorite) RED_COLOR else WHITE_COLOR
                                }
                        )

                    }

                    Column(
                        modifier = Modifier.padding(horizontal = 20.dp)
                    ) {

                        Box(
                            modifier = Modifier.height(20.dp)
                        )
                        Text(
                            text = movie.title,
                            fontSize = 28.sp,
                            color = WHITE_COLOR,
                            fontWeight = FontWeight.SemiBold
                        )
                        Box(
                            modifier = Modifier.height(10.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.baseline_star_rate_24),
                                contentDescription = "Rating",
                                tint = TEXT_GREY_COLOR,
                                modifier = Modifier.size(20.dp)
                            )
                            Box(
                                modifier = Modifier.width(10.dp)
                            )
                            Text(
                                text = "${movie.voteAverage} (IMDB)",
                                color = TEXT_GREY_COLOR,
                                fontSize = 18.sp
                            )
                        }
                        Box(
                            modifier = Modifier.height(10.dp)
                        )
                        HorizontalDivider(
                            color = TEXT_GREY_COLOR,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                        Box(
                            modifier = Modifier.height(10.dp)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "Release Date", color = WHITE_COLOR, fontSize = 22.sp)
                                Box(modifier = Modifier.height(15.dp))
                                Text(movie.releaseDate, color = TEXT_GREY_COLOR, fontSize = 18.sp)
                            }
                            Column(
                                modifier = Modifier
                                    .weight(1f),
                                verticalArrangement = Arrangement.SpaceBetween

                            ) {
                                Text(text = "Adult Only", color = WHITE_COLOR, fontSize = 22.sp)
                                Box(
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(30.dp))
                                        .background(if (movie.adult) Color.Red else Color.Green)
                                ) {
                                    Text(
                                        if (movie.adult) "Yes" else "No",
                                        modifier = Modifier.padding(
                                            horizontal = 10.dp,
                                            vertical = 3.dp
                                        ),
                                        color = WHITE_COLOR,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        }
                        Box(
                            modifier = Modifier.height(10.dp)
                        )
                        HorizontalDivider(
                            color = TEXT_GREY_COLOR,
                            modifier = Modifier.padding(horizontal = 5.dp)
                        )
                        Box(
                            modifier = Modifier.height(20.dp)
                        )
                        Text("Overview", color = WHITE_COLOR, fontSize = 22.sp)
                        Box(
                            modifier = Modifier.height(10.dp)
                        )
                        Text(movie.overview, color = TEXT_GREY_COLOR, fontSize = 18.sp)
                    }
                }
            }
        }
    }
}