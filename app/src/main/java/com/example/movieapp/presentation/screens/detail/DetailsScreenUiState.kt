package com.example.movieapp.presentation.screens.detail

import com.example.movieapp.domain.models.MovieDetailsDomain

sealed class DetailsScreenUiState {
    object Loading : DetailsScreenUiState()

    data class Loaded(
        val movie: MovieDetailsDomain, val tabs: List<DetailTab>, val isSaved: Boolean = false
    ) : DetailsScreenUiState()

    data class Error(
        val message: String
    ) : DetailsScreenUiState()

}