package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.models.MovieDetailsDomain

interface MovieOperatorUseCase {

    suspend fun saveMovie(movie: MovieDetailsDomain)

    suspend fun removeMovie(movieId: Int)
}
