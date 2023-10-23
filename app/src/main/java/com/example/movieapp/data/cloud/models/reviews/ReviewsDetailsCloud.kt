package com.example.movieapp.data.cloud.models.reviews


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewsDetailsCloud(
    @SerializedName("avatar_path")
    val avatarPath: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Double,
    @SerializedName("username")
    val username: String
): Parcelable