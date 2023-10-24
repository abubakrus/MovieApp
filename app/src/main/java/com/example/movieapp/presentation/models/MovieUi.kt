package com.example.movieapp.presentation.models


data class MovieUi(
    val backdropPath: String,
    val id: Int,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val voteAverage: Double,
    val runtime: Int,
) {

    fun isUnknown() = this == unknown

    companion object {
        val unknown = MovieUi(
            backdropPath = "error",
            id = -1,
            //     name = "error",
            posterPath = "error",
            voteAverage = 0.0,
            title = "error",
            releaseDate = "error",
            //      originalName = "error",
            runtime = 11
        )
    }

}