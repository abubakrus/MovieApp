package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.models.MovieDetailsDomain

interface FetchMovieByIdUseCase {
    suspend fun fetchMovieById(movieId: Int): MovieDetailsDomain?
}