package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.models.MovieDomain
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

class SearchMoviesByQueryUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : SearchMoviesByQueryUseCase {
    override suspend fun searchByQuery(query: String): List<MovieDomain> {
        return repository.searchByQuery(query)
    }
}