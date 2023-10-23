package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.models.MovieDomain
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMoviesInteractorImpl @Inject constructor(
    private val repository: MovieRepository
) : FetchMoviesInteractor {

    override suspend fun fetchNowPlayingMovies(): List<MovieDomain> {
        return repository.fetchNowPlayingMovie()
    }

    override suspend fun fetchPopularMovies(): List<MovieDomain> {
        return repository.fetchPopularMovie()
    }

    override suspend fun fetchTopRatedMovies(): List<MovieDomain> {
        return repository.fetchTopRatedMovie()
    }

    override suspend fun fetchUpComingMovies(): List<MovieDomain> {
        return repository.fetchUpcomingMovie()
    }
}