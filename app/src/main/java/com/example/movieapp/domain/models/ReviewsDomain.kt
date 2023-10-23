package com.example.movieapp.domain.models


import java.time.LocalDateTime
import java.util.UUID

data class ReviewsDomain(
    val author: String,
    val reviewsDetails: ReviewsDetailsDomain,
    val content: String,
    val id: String,
    val createdAt: LocalDateTime,

    ) {
    companion object {
        val unknown = ReviewsDomain(
            author = "cc,",
            content = "dd",
            id = UUID.randomUUID().toString(),
            reviewsDetails = ReviewsDetailsDomain.unknown,
            createdAt = LocalDateTime.now()
        )
    }
}