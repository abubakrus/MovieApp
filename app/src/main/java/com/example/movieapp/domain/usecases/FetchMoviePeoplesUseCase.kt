package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.models.ActorsDomain
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

interface FetchMoviePeoplesUseCase {
    suspend operator fun invoke(movieId: Int): ActorsDomain
}

class FetchMoviePeoplesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : FetchMoviePeoplesUseCase {
    override suspend fun invoke(movieId: Int): ActorsDomain {
        return repository.fetchMoviePeoples(movieId)
    }
}