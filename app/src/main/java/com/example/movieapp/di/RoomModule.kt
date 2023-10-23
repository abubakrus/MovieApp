package com.example.movieapp.di

import android.content.Context
import androidx.room.Room
import com.example.movieapp.data.cache.dao.MovieDao
import com.example.movieapp.data.cache.database.MovieDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


private const val MOVIE_DATABASE_NAME = "movies_database"


@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): MovieDatabase = Room.databaseBuilder(
        context, MovieDatabase::class.java, MOVIE_DATABASE_NAME
    ).build()

    @Provides
    fun provideMovieDao(
        database: MovieDatabase
    ): MovieDao = database.movieDao()
}