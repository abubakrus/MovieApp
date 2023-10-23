package com.example.movieapp.presentation.screens.main

import com.example.movieapp.presentation.models.MovieUi

sealed class MainScreenUiState {
    object Loading : MainScreenUiState()

    data class Loaded(
        val popularMovies: List<MovieUi>,
        val topRatedMovies: List<MovieUi>,
        val nowPlayingMovies: List<MovieUi>,
        val upComingMovies: List<MovieUi>
    ) : MainScreenUiState()

    data class Error(
        val message: String
    ) : MainScreenUiState()
}