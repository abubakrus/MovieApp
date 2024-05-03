package com.example.movieapp.data.cloud.mappers

import com.example.movieapp.data.cache.models.MovieDetailsCache
import com.example.movieapp.data.cloud.models.actors.ActorsResponse
import com.example.movieapp.data.cloud.models.actors.PeopleCloud
import com.example.movieapp.data.cloud.models.details.MovieDetailsResponse
import com.example.movieapp.data.cloud.models.movie.MovieResult
import com.example.movieapp.data.cloud.models.reviews.ReviewsCloud
import com.example.movieapp.data.cloud.remote.POSTER_PATH_URL
import com.example.movieapp.domain.models.ActorsDomain
import com.example.movieapp.domain.models.FetchTypeDomain
import com.example.movieapp.domain.models.MovieDetailsDomain
import com.example.movieapp.domain.models.MovieDomain
import com.example.movieapp.domain.models.PeopleDomain
import com.example.movieapp.domain.models.ReviewsDetailsDomain
import com.example.movieapp.domain.models.ReviewsDomain
import com.example.movieapp.presentation.models.FetchTypeUi
import com.example.movieapp.presentation.models.MovieUi
import java.time.LocalDateTime
import java.time.ZonedDateTime

fun MovieDetailsDomain.toCache(): MovieDetailsCache = this.run {
    MovieDetailsCache(
        id = id,
        backdropPath = POSTER_PATH_URL + backdropPath,
        movieGenresName = movieGenresName,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = POSTER_PATH_URL + posterPath,
        releaseDate = releaseDate,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount

    )
}


fun MovieDetailsCache.toDomain(): MovieDetailsDomain = this.run {
    MovieDetailsDomain(
        id = id,
        backdropPath = backdropPath,
        movieGenresName = movieGenresName,
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = posterPath,
        releaseDate = releaseDate,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount
    )
}


fun MovieDetailsResponse.toDomain(): MovieDetailsDomain = this.run {
    MovieDetailsDomain(
        id = id,
        backdropPath = POSTER_PATH_URL + backdropPath,
        movieGenresName = movieGenres.map { it.name },
        originalTitle = originalTitle,
        overview = overview,
        popularity = popularity,
        posterPath = POSTER_PATH_URL + posterPath,
        releaseDate = releaseDate,
        runtime = runtime,
        status = status,
        tagline = tagline,
        title = title,
        voteAverage = voteAverage,
        voteCount = voteCount

    )
}

fun MovieResult.toDomain(): MovieDomain = this.run {
    MovieDomain(
        id = id,
        backdropPath = backdropPath ?: posterPath ?: "",
        posterPath = posterPath ?: "",
        releaseDate = releaseDate,
        title = title,
        voteAverage = voteAverage,
        runtime = runtime
    )
}


fun List<MovieDomain>.toDomain(): List<MovieUi> {
    return this.map { movieDomain ->
        MovieUi(
            backdropPath = movieDomain.backdropPath ?: "",
            id = movieDomain.id,
            posterPath = movieDomain.posterPath ?: "",
            releaseDate = movieDomain.releaseDate,
            voteAverage = movieDomain.voteAverage,
            title = movieDomain.title,
            runtime = movieDomain.runtime
        )
    }
}


fun PeopleCloud.toDomain() = this.run {
    PeopleDomain(
        castId = castId,
        character = character,
        creditId = creditId,
        id = id,
        name = name,
        originalName = originalName,
        popularity = popularity,
        profilePath = POSTER_PATH_URL + profilePath
    )
}

fun ActorsResponse.toDomain() = this.run {
    ActorsDomain(
        id = id,
        crews = crewCloud.map { it.toDomain() },
        peoples = peopleCloud.map { it.toDomain() }
    )
}

fun ReviewsCloud.toDomain() = this.run {
    ReviewsDomain(
        author = author,
        id = id,
        content = content,
        reviewsDetails = ReviewsDetailsDomain(
            avatarPath = POSTER_PATH_URL + reviewsDetailsCloud.avatarPath,
            name = reviewsDetailsCloud.name,
            username = reviewsDetailsCloud.username,
            rating = reviewsDetailsCloud.rating,
        ),
        createdAt = ZonedDateTime.parse(createdAt).toLocalDateTime()
    )
}

fun List<MovieDomain>.toUi(): List<MovieUi> {
    return this.map { movieDomain ->
        MovieUi(
            backdropPath = POSTER_PATH_URL + movieDomain.backdropPath,
            id = movieDomain.id,
            posterPath = POSTER_PATH_URL + movieDomain.posterPath,
            voteAverage = movieDomain.voteAverage,
            title = movieDomain.title,
            releaseDate = movieDomain.releaseDate,
            runtime = movieDomain.runtime
        )
    }
}

fun List<MovieUi>.isUnknown(): Boolean {
    MovieUi(
        backdropPath = "error",
        id = -1,
        posterPath = "error",
        voteAverage = 0.0,
        title = "error",
        releaseDate = "error",
        runtime = 11
    )
    return this.isEmpty()
}

fun FetchTypeUi.toDomain(): FetchTypeDomain = when (this) {
    FetchTypeUi.POPULAR -> FetchTypeDomain.POPULAR
    FetchTypeUi.UP_COMING -> FetchTypeDomain.UP_COMING
    FetchTypeUi.NOW_PLAYING -> FetchTypeDomain.NOW_PLAYING
    FetchTypeUi.TOP_RATED -> FetchTypeDomain.TOP_RATED
}
