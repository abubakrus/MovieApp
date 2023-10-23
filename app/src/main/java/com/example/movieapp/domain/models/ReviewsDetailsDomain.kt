package com.example.movieapp.domain.models


data class ReviewsDetailsDomain(
    val avatarPath: String,
    val name: String,
    val rating: Double,
    val username: String
) {
    companion object {
        val unknown = ReviewsDetailsDomain(
            avatarPath = String(),
            name = "ddd",
            rating = 1.1,
            username = "sss"
        )
    }
}