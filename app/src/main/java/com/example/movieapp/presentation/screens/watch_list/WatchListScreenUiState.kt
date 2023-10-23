package com.example.movieapp.presentation.screens.watch_list

import com.example.movieapp.domain.models.MovieDetailsDomain

data class WatchListScreenUiState(
    val movies: List<MovieDetailsDomain> = emptyList()
)