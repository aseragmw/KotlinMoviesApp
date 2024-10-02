package com.example.kotlinmoviesapp.main

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlinmoviesapp.core.constants.NOW_PLAYING_KEY
import com.example.kotlinmoviesapp.core.constants.TOP_RATED_KEY
import com.example.kotlinmoviesapp.core.utils.SharedPrefHelper
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity
import com.example.kotlinmoviesapp.features.movies.presentation.screens.home.screens.HomeScreen
import com.example.kotlinmoviesapp.features.movies.presentation.screens.movie_details.screens.MovieDetailsScreen
import com.example.kotlinmoviesapp.features.movies.presentation.viewmodels.MoviesViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import isInternetAvailable


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MoviesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        installSplashScreen().setKeepOnScreenCondition {
            val isMoviesReady = (viewModel.topRatedMovies.value != null) &&
                    (viewModel.nowPlayingMovies.value != null)
            isMoviesReady
        }

        if (SharedPrefHelper.isFirstTime(this)) {
            showSyncOptionsDialog()
        } else {
            checkForSync()
        }

        viewModel.getAllMovies(TOP_RATED_KEY)
        viewModel.getAllMovies(NOW_PLAYING_KEY)
        viewModel.getAllFavorites()
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = "home"
            ) {
                composable("home") {
                    HomeScreen(viewModel, navController)
                }
                composable("details") {
                    MovieDetailsScreen(viewModel, navController)
                }
            }
        }
    }

    private fun showSyncOptionsDialog() {
        val syncOptions = arrayOf("Never sync", "Sync every 8 hour", "Sync every day")

        AlertDialog.Builder(this)
            .setTitle("Choose Sync Option")
            .setSingleChoiceItems(syncOptions, -1) { dialog, which ->
                when (which) {
                    0 -> SharedPrefHelper.setSyncOption(this, 0)
                    1 -> SharedPrefHelper.setSyncOption(this, 8)
                    2 -> SharedPrefHelper.setSyncOption(this, 24)
                }
            }
            .setPositiveButton("OK") { dialog, _ ->
                SharedPrefHelper.setFirstTime(this, false)
                dialog.dismiss()
                checkForSync()
            }
            .setCancelable(false)
            .show()
    }

    private fun checkForSync() {
        val syncOption = SharedPrefHelper.getSyncOption(this)
        val lastSyncTime = SharedPrefHelper.getLastSync(this)
        val currentTime = System.currentTimeMillis()

        val syncInterval: Long = when (syncOption) {
            8 -> 8 * 60 * 60 * 1000L
            24 -> 24 * 60 * 60 * 1000L
            else -> -1
        }
        if (syncInterval != -1L && (currentTime - lastSyncTime >= syncInterval)) {
            syncData()
            SharedPrefHelper.setLastSync(this, currentTime)
        }
    }

    private fun syncData() {
        if(isInternetAvailable(this)){
            viewModel.updateAllMovies(TOP_RATED_KEY)
            viewModel.updateAllMovies(NOW_PLAYING_KEY)
        }
        else{
            Toast.makeText(this,"No Internet Connection",Toast.LENGTH_SHORT).show()
        }
    }
}
