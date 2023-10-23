package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.models.MovieDetailsDomain
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class MovieOperatorUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : MovieOperatorUseCase {
    override suspend fun saveMovie(movie: MovieDetailsDomain) {
        repository.addNewMovie(movie)
    }

    override suspend fun removeMovie(movieId: Int) {
        repository.deleteMovieById(movieId)
    }
}