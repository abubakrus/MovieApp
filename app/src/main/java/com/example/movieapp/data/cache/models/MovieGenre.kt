package com.example.movieapp.data.cache.models

import androidx.room.ColumnInfo


data class MovieGenreCache(
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("name")
    val name: String
)