package com.example.kotlinmoviesapp.core.db

import android.content.Context
import androidx.room.Room
import javax.inject.Inject

class MoviesDbBuilder @Inject constructor(
    val context: Context
) {
    private var INSTANCE: MoviesDB? = null

    fun getMoviesDB(): MoviesDB {
        synchronized(this){
            if(INSTANCE==null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MoviesDB::class.java,
                    "MoviesDB"
                ).fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }
}