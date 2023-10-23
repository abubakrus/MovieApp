package com.example.movieapp.data.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.data.cache.models.MovieDetailsCache
import kotlinx.coroutines.flow.Flow


@Dao
interface MovieDao {

    @Insert
    fun addNewMovie(movie: MovieDetailsCache)

    @Query("DELETE FROM movies_table WHERE id = :movieId")
    suspend fun deleteMovieById(movieId: Int)

    @Query("SELECT * FROM movies_table")
    fun fetchAllSavedMovies(): Flow<List<MovieDetailsCache>>

    @Query("SELECT EXISTS (SELECT * FROM movies_table WHERE id = :movieId)")
    fun isMovieSaved(movieId: Int): kotlinx.coroutines.flow.Flow<Boolean>
}