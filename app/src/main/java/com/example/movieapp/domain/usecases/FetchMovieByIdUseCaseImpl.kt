package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.models.MovieDetailsDomain
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class FetchMovieByIdUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : FetchMovieByIdUseCase {
    override suspend fun fetchMovieById(movieId: Int): MovieDetailsDomain? {
        return repository.fetchMovieById(movieId)
    }
}