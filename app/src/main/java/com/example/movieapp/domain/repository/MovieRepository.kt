package com.example.movieapp.domain.repository

import com.example.movieapp.domain.models.ActorsDomain
import com.example.movieapp.domain.models.MovieDetailsDomain
import com.example.movieapp.domain.models.MovieDomain
import com.example.movieapp.domain.models.ReviewsDomain
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    fun isMovieSavedFlow(movieId: Int): Flow<Boolean>
    suspend fun fetchNowPlayingMovie(): List<MovieDomain>
    suspend fun fetchPopularMovie(): List<MovieDomain>
    suspend fun fetchTopRatedMovie(): List<MovieDomain>
    suspend fun fetchUpcomingMovie(): List<MovieDomain>
    suspend fun fetchMovieById(movieId: Int): MovieDetailsDomain?
    suspend fun addNewMovie(movie: MovieDetailsDomain)
    suspend fun deleteMovieById(movieId: Int)
    fun fetchAllSavedMovies(): Flow<List<MovieDetailsDomain>>
    suspend fun searchByQuery(query: String): List<MovieDomain>
    suspend fun fetchMoviePeoples(movieId: Int): ActorsDomain
    suspend fun fetchMovieReviews(movieId: Int): List<ReviewsDomain>
}