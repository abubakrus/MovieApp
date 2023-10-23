package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.models.MovieDomain

interface FetchMoviesInteractor {
    suspend fun fetchNowPlayingMovies(): List<MovieDomain>
    suspend fun fetchPopularMovies(): List<MovieDomain>
    suspend fun fetchTopRatedMovies(): List<MovieDomain>
    suspend fun fetchUpComingMovies(): List<MovieDomain>
}