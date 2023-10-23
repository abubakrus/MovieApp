package com.example.movieapp.data.cloud.repository

import com.example.movieapp.data.cache.source.MovieCacheDataSource
import com.example.movieapp.data.cloud.mappers.toCache
import com.example.movieapp.data.cloud.mappers.toDomain
import com.example.movieapp.data.cloud.source.MovieCloudDataSource
import com.example.movieapp.domain.models.ActorsDomain
import com.example.movieapp.domain.models.MovieDetailsDomain
import com.example.movieapp.domain.models.MovieDomain
import com.example.movieapp.domain.models.ReviewsDomain
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val cloudDataSource: MovieCloudDataSource,
    private val cacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override fun isMovieSavedFlow(movieId: Int): Flow<Boolean> {
        return cacheDataSource.isMovieSavedFlow(movieId)
    }

    override suspend fun fetchNowPlayingMovie(): List<MovieDomain> {
        return cloudDataSource.fetchNowPlayingMovie().map { it.toDomain() }
    }

    override suspend fun fetchPopularMovie(): List<MovieDomain> {
        return cloudDataSource.fetchPopularMovie().map { it.toDomain() }
    }

    override suspend fun fetchTopRatedMovie(): List<MovieDomain> {
        return cloudDataSource.fetchTopRatedMovie().map { it.toDomain() }
    }

    override suspend fun fetchUpcomingMovie(): List<MovieDomain> {
        return cloudDataSource.fetchUpcomingMovie().map { it.toDomain() }
    }

    override suspend fun fetchMovieById(movieId: Int): MovieDetailsDomain? {
        return cloudDataSource.fetchMovieById(movieId)?.toDomain()
    }

    override suspend fun addNewMovie(movie: MovieDetailsDomain) {
        cacheDataSource.addNewMovie(movie.toCache())
    }


    override suspend fun deleteMovieById(movieId: Int) {
        cacheDataSource.deleteMovieById(movieId)
    }

    override fun fetchAllSavedMovies(): Flow<List<MovieDetailsDomain>> {
        return cacheDataSource.fetchAllSavedMovies().map { it.map { it.toDomain() } }
    }


    override suspend fun searchByQuery(query: String): List<MovieDomain> {
        return cloudDataSource.searchByQuery(query).map { it.toDomain() }
    }

    override suspend fun fetchMoviePeoples(movieId: Int): ActorsDomain {
        return cloudDataSource.fetchMoviePeoples(movieId).toDomain()
    }

    override suspend fun fetchMovieReviews(movieId: Int): List<ReviewsDomain> {
        return cloudDataSource.fetchMovieReviews(movieId).map { it.toDomain() }
    }

}