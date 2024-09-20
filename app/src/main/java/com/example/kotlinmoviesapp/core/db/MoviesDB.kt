package com.example.kotlinmoviesapp.core.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity

@Database(entities = [MovieEntity::class], version = 1, exportSchema = true,)
abstract class MoviesDB : RoomDatabase() {
    abstract fun moviesDao(): MoviesDAO
}