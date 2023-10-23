package com.example.movieapp.data.cloud.remote

import com.example.movieapp.data.cloud.models.actors.ActorsResponse
import com.example.movieapp.data.cloud.models.details.MovieDetailsResponse
import com.example.movieapp.data.cloud.models.movie.MoviesResponse
import com.example.movieapp.data.cloud.models.reviews.ReviewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


const val POSTER_PATH_URL = "https://image.tmdb.org/t/p/w342"


interface MovieService {
    @GET("movie/now_playing")
    suspend fun fetchNowPlayingMovie(): Response<MoviesResponse>

    @GET("movie/popular")
    suspend fun fetchPopularMovie(): Response<MoviesResponse>

    @GET("movie/top_rated")
    suspend fun fetchTopRatedMovie(): Response<MoviesResponse>

    @GET("movie/upcoming")
    suspend fun fetchUpcomingMovie(): Response<MoviesResponse>

    @GET("search/movie")
    suspend fun searchByQuery(
        @Query("query") query: String
    ): Response<MoviesResponse>

    @GET("movie/{movie_id}")
    suspend fun fetchMovieById(
        @Path("movie_id") movieId: Int,
    ): Response<MovieDetailsResponse>

    @GET("movie/{movie_id}/credits")
    suspend fun fetchMovieActors(
        @Path("movie_id") movieId: Int,
    ): Response<ActorsResponse>

    @GET("movie/{movie_id}/reviews")
    suspend fun fetchMovieReviews(
        @Path("movie_id") movieId: Int,
    ): Response<ReviewsResponse>
}