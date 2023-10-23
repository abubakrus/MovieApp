package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.models.MovieDetailsDomain
import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FetchAllSavedMoviesUseCase {
    operator fun invoke(): Flow<List<MovieDetailsDomain>>

}

class FetchAllSavedMoviesImpl @Inject constructor(
    private val movieRepository: MovieRepository
) : FetchAllSavedMoviesUseCase {
    override fun invoke(): Flow<List<MovieDetailsDomain>> {
        return movieRepository.fetchAllSavedMovies()
    }
}