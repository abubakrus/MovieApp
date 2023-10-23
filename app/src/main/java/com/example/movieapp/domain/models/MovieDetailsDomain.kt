package com.example.movieapp.domain.models

import java.util.UUID


data class MovieDetailsDomain(
    val backdropPath: String,
    val movieGenresName: List<String>,
    val id: Int,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val status: String,
    val tagline: String,
    val title: String,
    val voteAverage: Double,
    val voteCount: Int
) {
    companion object {
        val preview = MovieDetailsDomain(
            id = UUID.randomUUID().hashCode(),
            backdropPath = "",
            posterPath = "",
            releaseDate = "12.12.1201",
            title = "Spider-man",
            voteAverage = 10.0,
            movieGenresName = listOf("Actions"),
            originalTitle = "Spider-man",
            overview = "",
            popularity = 10.1,
            runtime = 1,
            status = "",
            tagline = "",
            voteCount = 1
        )
    }
}