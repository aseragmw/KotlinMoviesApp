package com.example.kotlinmoviesapp.core.di.modules

import android.content.Context
import com.example.kotlinmoviesapp.core.db.MoviesDAO
import com.example.kotlinmoviesapp.core.db.MoviesDbBuilder
import com.example.kotlinmoviesapp.features.movies.data.datasources.local.MoviesLocalDataSource
import com.example.kotlinmoviesapp.features.movies.data.datasources.remote.MoviesRemoteDataSource
import com.example.kotlinmoviesapp.features.movies.data.repo.MoviesRepoImpl
import com.example.kotlinmoviesapp.features.movies.domain.repo.MoviesRepo
import com.example.kotlinmoviesapp.features.movies.domain.usecases.GetAllFavouritesUsecase
import com.example.kotlinmoviesapp.features.movies.domain.usecases.GetAllMoviesUsecase
import com.example.kotlinmoviesapp.features.movies.domain.usecases.GetMovieByIdUsecase
import com.example.kotlinmoviesapp.features.movies.domain.usecases.UpdateAllMoviesUsecase
import com.example.kotlinmoviesapp.features.movies.domain.usecases.UpdateMovieUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideMoviesRepo(
        localDataSource: MoviesLocalDataSource,
        remoteDataSource: MoviesRemoteDataSource
    ): MoviesRepo = MoviesRepoImpl(remoteDataSource, localDataSource)


    @Provides
    @Singleton
    fun provideMoviesDAO(@ApplicationContext context: Context): MoviesDAO = MoviesDbBuilder(context).getMoviesDB().moviesDao()



    @Provides
    @Singleton
    fun providesMoviesRemoteDataSource(@ApplicationContext context: Context): MoviesRemoteDataSource = MoviesRemoteDataSource(context)

    @Provides
    @Singleton
    fun providesMoviesLocalDataSource(
        dao : MoviesDAO
    ): MoviesLocalDataSource = MoviesLocalDataSource(dao)

    @Provides
    @Singleton
    fun providesGetAllMoviesUsecase(MoviesRepo: MoviesRepo): GetAllMoviesUsecase = GetAllMoviesUsecase(MoviesRepo)

    @Provides
    @Singleton
    fun providesUpdateAllMoviesUsecase(MoviesRepo: MoviesRepo): UpdateAllMoviesUsecase = UpdateAllMoviesUsecase(MoviesRepo)

    @Provides
    @Singleton
    fun providesGetMovieByIdUsecase(MoviesRepo: MoviesRepo): GetMovieByIdUsecase = GetMovieByIdUsecase(MoviesRepo)

    @Provides
    @Singleton
    fun providesUpdateMovieUsecase(MoviesRepo: MoviesRepo): UpdateMovieUsecase = UpdateMovieUsecase(MoviesRepo)

    @Provides
    @Singleton
    fun providesGetAllFavouritesUsecase(MoviesRepo: MoviesRepo): GetAllFavouritesUsecase = GetAllFavouritesUsecase(MoviesRepo)

}