package com.example.movieapp.presentation.screens.detail

import androidx.annotation.StringRes
import com.example.movieapp.R
import com.example.movieapp.domain.models.PeopleDomain
import com.example.movieapp.domain.models.ReviewsDomain
import kotlinx.coroutines.flow.StateFlow

sealed class DetailTab(
    @StringRes
    val titleResId: Int
) {
    data class AboutMovie(
        val about: String
    ) : DetailTab(R.string.about_movie)

    data class Reviewers(
        val reviews: StateFlow<List<ReviewsDomain>>
    ) : DetailTab(R.string.reviews)

    data class Casts(
        val casts: List<PeopleDomain>
    ) : DetailTab(R.string.cast)

    data class Crews(
        val crews: List<PeopleDomain>
    ) : DetailTab(R.string.crews)

}