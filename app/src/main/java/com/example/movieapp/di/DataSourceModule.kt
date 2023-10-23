package com.example.movieapp.di

import com.example.movieapp.data.cache.dao.MovieDao
import com.example.movieapp.data.cache.source.MovieCacheDataSource
import com.example.movieapp.data.cache.source.MovieCacheDataSourceImpl
import com.example.movieapp.data.cloud.remote.MovieService
import com.example.movieapp.data.cloud.source.MovieCloudDataSource
import com.example.movieapp.data.cloud.source.MovieCloudDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Provides
    fun provideMovieCacheDataSource(
        movieDao: MovieDao
    ): MovieCacheDataSource = MovieCacheDataSourceImpl(movieDao)

    @Provides
    fun provideMovieCloudDataSource(
        movieService: MovieService
    ): MovieCloudDataSource = MovieCloudDataSourceImpl(movieService)


}