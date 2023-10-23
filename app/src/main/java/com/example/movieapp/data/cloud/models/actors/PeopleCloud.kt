package com.example.movieapp.data.cloud.models.actors


import android.os.Parcelable
import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class PeopleCloud(
    @SerializedName("cast_id")
    val castId: Int,
    @SerializedName("character")
    val character: String?,
    @SerializedName("credit_id")
    val creditId: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("original_name")
    val originalName: String?,
    @SerializedName("popularity")
    val popularity: Double?,
    @SerializedName("profile_path")
    val profilePath: String?
) : Parcelable