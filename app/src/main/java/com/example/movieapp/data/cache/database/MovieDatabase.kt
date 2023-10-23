package com.example.movieapp.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.movieapp.data.cache.dao.MovieDao
import com.example.movieapp.data.cache.models.MovieDetailsCache
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Database(
    entities = [
        MovieDetailsCache::class],
    version = 1,

    )
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

}

object Converters {
    @TypeConverter
    fun fromString(value: String?): List<String> {
        val listType: Type = object : TypeToken<List<String?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<String?>?): String {
        val gson= Gson()
        return gson.toJson(list)
    }
}