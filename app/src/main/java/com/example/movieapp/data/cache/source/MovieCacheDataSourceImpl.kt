package com.example.movieapp.data.cache.source

import android.util.Log
import com.example.movieapp.data.cache.dao.MovieDao
import com.example.movieapp.data.cache.models.MovieDetailsCache
import kotlinx.coroutines.flow.Flow

class MovieCacheDataSourceImpl(
    private val movieDao: MovieDao
) : MovieCacheDataSource {

    override suspend fun addNewMovie(movie: MovieDetailsCache) {
        movieDao.addNewMovie(movie)
    }

    override suspend fun deleteMovieById(movieId: Int) {
        movieDao.deleteMovieById(movieId)
    }

    override  fun fetchAllSavedMovies(): Flow<List<MovieDetailsCache>> {
        return movieDao.fetchAllSavedMovies()
    }

    override  fun isMovieSavedFlow(movieId: Int): Flow<Boolean> {
        return movieDao.isMovieSaved(movieId)
    }
}