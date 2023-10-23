package com.example.movieapp.domain.usecases

import com.example.movieapp.domain.models.ReviewsDomain
import com.example.movieapp.domain.repository.MovieRepository
import javax.inject.Inject

interface FetchMovieReviewsUseCase {
    suspend operator fun invoke(movieId: Int): List<ReviewsDomain>
}

data class FetchMovieReviewsUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : FetchMovieReviewsUseCase {
    override suspend fun invoke(movieId: Int): List<ReviewsDomain> {
        return repository.fetchMovieReviews(movieId)
    }

}