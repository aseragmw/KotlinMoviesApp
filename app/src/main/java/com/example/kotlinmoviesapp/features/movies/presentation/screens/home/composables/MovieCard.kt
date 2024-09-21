package com.example.kotlinmoviesapp.features.movies.presentation.screens.home.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.kotlinmoviesapp.core.utils.RED_COLOR
import com.example.kotlinmoviesapp.core.utils.TEXT_GREY_COLOR
import com.example.kotlinmoviesapp.core.utils.WHITE_COLOR
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity
import com.example.kotlinmoviesapp.features.movies.presentation.viewmodels.MoviesViewModel
import com.google.gson.Gson

@Composable
fun MovieCard(viewModel: MoviesViewModel, movie: MovieEntity, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth(0.5f)
            .padding(horizontal = 5.dp)
            .clickable {
                viewModel.getMovieById(movie.id)
                navController.navigate("details")
            }

    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(horizontal = 3.dp)
        ) {
            Box {
                AsyncImage(
                    model = movie.posterPath,
                    contentDescription = movie.title,
                    modifier = Modifier
                        .fillMaxSize()
                        .clip(RoundedCornerShape(20.dp)),
                )
                Icon(
                    imageVector= Icons.Default.Favorite,
                    contentDescription = "Favourite",
                    tint = if(movie.isFavorite) RED_COLOR else WHITE_COLOR,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .padding(end = 15.dp, bottom = 10.dp)
                        .align(Alignment.BottomEnd)
                        .size(30.dp)
                        .clickable {
                            viewModel.addOrRemoveFavorite(movie)
                        }
                )
            }
            Box(modifier= Modifier.height(10.dp))
            Text(
                text = movie.title,
                color = WHITE_COLOR,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
            Box(modifier= Modifier.height(5.dp))
            Text(
                text = "(${movie.releaseDate.take(4)})",
                color = TEXT_GREY_COLOR,
                fontSize = 20.sp
            )
            Box(modifier= Modifier.height(10.dp))

        }

    }
}