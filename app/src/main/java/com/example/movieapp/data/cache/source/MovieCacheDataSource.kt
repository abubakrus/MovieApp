package com.example.movieapp.data.cache.source

import com.example.movieapp.data.cache.models.MovieDetailsCache
import com.example.movieapp.domain.models.MovieDetailsDomain
import kotlinx.coroutines.flow.Flow

interface MovieCacheDataSource {

    suspend fun addNewMovie(movie: MovieDetailsCache)

    suspend fun deleteMovieById(movieId: Int)

    fun fetchAllSavedMovies(): Flow<List<MovieDetailsCache>>

    fun isMovieSavedFlow(movieId: Int): Flow<Boolean>
}