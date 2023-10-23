package com.example.movieapp.domain.models

data class MovieDomain(
    val backdropPath: String,
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val runtime: Int
) {
    companion object {
        val unknown = MovieDomain(
            backdropPath = "",
            id = 0,
            posterPath = "",
            releaseDate = "",
            title = "",
            voteAverage = 0.0,
            runtime = 11
        )
    }
}