package com.example.movieapp.data.cloud.models.reviews


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewsCloud(
    @SerializedName("author")
    val author: String,
    @SerializedName("author_details")
    val reviewsDetailsCloud: ReviewsDetailsCloud,
    @SerializedName("content")
    val content: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("created_at")
    val createdAt: String,
) : Parcelable