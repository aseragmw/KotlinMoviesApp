package com.example.kotlinmoviesapp.core.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.kotlinmoviesapp.features.movies.domain.entities.MovieEntity

@Dao
interface MoviesDAO {
    @Insert
    suspend fun insertAllMovies(movies:List<MovieEntity>)

    @Query("SELECT * FROM Movies where category = :category")
    fun getAllMovies(category:String):LiveData<List<MovieEntity>>

    @Query("DELETE FROM Movies where category = :category")
    suspend fun deleteAllMovies(category: String)

    @Query("SELECT * FROM Movies where id = :id")
    fun getMovieById(id:Int):LiveData<MovieEntity>

    @Update
    suspend fun updateMovie(movie:MovieEntity)

    @Query("SELECT * FROM Movies where isFavorite = 1")
    fun getAllFavorites():LiveData<List<MovieEntity>>
    @Query("SELECT * FROM Movies where isFavorite = 1")
    fun getAllFavoritesAsList():List<MovieEntity>

}