package com.example.movieapp.data.cloud.source

import com.example.movieapp.data.cloud.models.actors.ActorsResponse
import com.example.movieapp.data.cloud.models.details.MovieDetailsResponse
import com.example.movieapp.data.cloud.models.movie.MovieResult
import com.example.movieapp.data.cloud.models.reviews.ReviewsCloud

interface MovieCloudDataSource {
    suspend fun fetchNowPlayingMovie(): List<MovieResult>

    suspend fun fetchPopularMovie(): List<MovieResult>

    suspend fun fetchTopRatedMovie(): List<MovieResult>

    suspend fun fetchUpcomingMovie(): List<MovieResult>

    suspend fun searchByQuery(query: String): List<MovieResult>

    suspend fun fetchMovieById(movieId: Int): MovieDetailsResponse?

    suspend fun fetchMoviePeoples(movieId: Int): ActorsResponse

    suspend fun fetchMovieReviews(movieId: Int): List<ReviewsCloud>

}