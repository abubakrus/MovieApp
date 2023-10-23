package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.models.MovieDomain

interface SearchMoviesByQueryUseCase {
    suspend fun searchByQuery(query: String): List<MovieDomain>
}