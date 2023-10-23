package com.example.movieapp.domain.usecases

import kotlinx.coroutines.flow.Flow

interface IsMovieSavedUseCase {
    operator fun invoke(movieId: Int): Flow<Boolean>
}