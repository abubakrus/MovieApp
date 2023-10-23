package com.example.movieapp.presentation.models

data class MovieCategoriesModels(
    val titleResId: String,
    val categoryType: FetchTypeUi,
) {
    companion object {
        fun getAllMovieCategories() = listOf(
            MovieCategoriesModels(
                titleResId = "Popular",
                categoryType = FetchTypeUi.POPULAR
            ),
            MovieCategoriesModels(
                titleResId = "UpComing",
                categoryType = FetchTypeUi.UP_COMING
            ),
            MovieCategoriesModels(
                titleResId = "NowPlaying",
                categoryType = FetchTypeUi.NOW_PLAYING
            ),
            MovieCategoriesModels(
                titleResId = "TopRated",
                categoryType = FetchTypeUi.TOP_RATED
            ),
        )
    }
}


