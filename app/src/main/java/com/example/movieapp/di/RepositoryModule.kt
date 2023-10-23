package com.example.movieapp.di

import com.example.movieapp.data.cache.source.MovieCacheDataSource
import com.example.movieapp.data.cloud.remote.MovieService
import com.example.movieapp.data.cloud.repository.MovieRepositoryImpl
import com.example.movieapp.data.cloud.source.MovieCloudDataSource
import com.example.movieapp.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    fun provideMovieRepository(
        cloudDataSource: MovieCloudDataSource, cacheDataSource: MovieCacheDataSource
    ): MovieRepository = MovieRepositoryImpl(
        cloudDataSource = cloudDataSource, cacheDataSource = cacheDataSource
    )
}