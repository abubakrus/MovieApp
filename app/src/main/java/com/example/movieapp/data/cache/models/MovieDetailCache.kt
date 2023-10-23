package com.example.movieapp.data.cache.models


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "movies_table"
)
data class MovieDetailsCache(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo("backdrop_path")
    val backdropPath: String,
    @ColumnInfo("genres")
    val movieGenresName: List<String>,
    @ColumnInfo("original_title")
    val originalTitle: String,
    @ColumnInfo("overview")
    val overview: String,
    @ColumnInfo("popularity")
    val popularity: Double,
    @ColumnInfo("poster_path")
    val posterPath: String,
    @ColumnInfo("release_date")
    val releaseDate: String,
    @ColumnInfo("runtime")
    val runtime: Int,
    @ColumnInfo("status")
    val status: String,
    @ColumnInfo("tagline")
    val tagline: String,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("vote_average")
    val voteAverage: Double,
    @ColumnInfo("vote_count")
    val voteCount: Int
)