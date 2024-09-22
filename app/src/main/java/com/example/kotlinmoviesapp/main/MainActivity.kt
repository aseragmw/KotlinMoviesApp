package com.example.kotlinmoviesapp.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinmoviesapp.core.constants.NOW_PLAYING_KEY
import com.example.kotlinmoviesapp.core.constants.TOP_RATED_KEY
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity
import com.example.kotlinmoviesapp.features.movies.presentation.screens.home.screens.HomeScreen
import com.example.kotlinmoviesapp.features.movies.presentation.screens.movie_details.screens.MovieDetailsScreen
import com.example.kotlinmoviesapp.features.movies.presentation.viewmodels.MoviesViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import isInternetAvailable


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val viewModel: MoviesViewModel by viewModels()
        if(isInternetAvailable(this)){
            viewModel.updateAllMovies(TOP_RATED_KEY)
            viewModel.updateAllMovies(NOW_PLAYING_KEY)
        }
        else{
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show()
        }
        viewModel.getAllMovies(TOP_RATED_KEY)
        viewModel.getAllMovies(NOW_PLAYING_KEY)
        viewModel.getAllFavorites()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "home"
            ){
                composable("home"){
                    HomeScreen(viewModel,navController)
                }
                composable("details"){
                    MovieDetailsScreen(viewModel,navController)
                }
            }
        }
    }
}
