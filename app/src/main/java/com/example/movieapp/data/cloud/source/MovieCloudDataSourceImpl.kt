package com.example.movieapp.data.cloud.source

import com.example.movieapp.data.cloud.models.actors.ActorsResponse
import com.example.movieapp.data.cloud.models.details.MovieDetailsResponse
import com.example.movieapp.data.cloud.models.movie.MovieResult
import com.example.movieapp.data.cloud.models.reviews.ReviewsCloud
import com.example.movieapp.data.cloud.remote.MovieService

class MovieCloudDataSourceImpl(
    private val movieService: MovieService,
) : MovieCloudDataSource {
    override suspend fun fetchNowPlayingMovie(): List<MovieResult> {
        return try {
            val movieResponse = movieService.fetchNowPlayingMovie()
            if (movieResponse.isSuccessful) {
                movieResponse.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            emptyList()
        }

    }

    override suspend fun fetchPopularMovie(): List<MovieResult> {
        return try {
            val movieResponse = movieService.fetchPopularMovie()
            if (movieResponse.isSuccessful) {
                movieResponse.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            emptyList()
        }
    }

    override suspend fun fetchTopRatedMovie(): List<MovieResult> {
        return try {
            val movieResponse = movieService.fetchTopRatedMovie()
            if (movieResponse.isSuccessful) {
                movieResponse.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            emptyList()
        }
    }

    override suspend fun fetchUpcomingMovie(): List<MovieResult> {
        return try {
            val movieResponse = movieService.fetchUpcomingMovie()
            if (movieResponse.isSuccessful) {
                movieResponse.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            emptyList()
        }
    }

    override suspend fun searchByQuery(query: String): List<MovieResult> {
        return try {
            val movieResponse = movieService.searchByQuery(query)
            if (movieResponse.isSuccessful) {
                movieResponse.body()?.results ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Throwable) {
            emptyList()
        }
    }


    override suspend fun fetchMovieById(movieId: Int): MovieDetailsResponse? {
        return try {
            val movieResponse = movieService.fetchMovieById(movieId)
            if (movieResponse.isSuccessful) {
                movieResponse.body()
            } else {
                null
            }
        } catch (e: Throwable) {
            null
        }
    }

    override suspend fun fetchMoviePeoples(movieId: Int): ActorsResponse {
        return try {
            val response = movieService.fetchMovieActors(movieId)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!
            } else ActorsResponse.unknown

        } catch (e: Throwable) {
            ActorsResponse.unknown
        }
    }

    override suspend fun fetchMovieReviews(movieId: Int): List<ReviewsCloud> {
        return try {
            val response = movieService.fetchMovieReviews(movieId)
            if (response.isSuccessful && response.body() != null) {
                response.body()!!.reviewsClouds
            } else emptyList()
        } catch (e: Throwable) {
            emptyList()
        }
    }
}