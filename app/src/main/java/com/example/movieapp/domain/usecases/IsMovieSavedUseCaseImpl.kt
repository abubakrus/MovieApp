package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class IsMovieSavedUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : IsMovieSavedUseCase {
    override fun invoke(movieId: Int): Flow<Boolean> {
        return repository.isMovieSavedFlow(movieId)
    }

}