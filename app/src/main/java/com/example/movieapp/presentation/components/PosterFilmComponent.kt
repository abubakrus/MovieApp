package com.example.movieapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.movieapp.R
import com.example.movieapp.presentation.models.MovieUi


@Preview
@Composable
fun PosterFilmComponentsPreview(
    navController: NavHostController,
    navigateToDetails: (Int) -> Unit
) {
    PosterFilmComponent(
        movie = MovieUi.unknown,
        navigateToDetails = navigateToDetails
    )
}

@Composable
fun PosterFilmComponent(
    navigateToDetails: (Int) -> Unit,
    movie: MovieUi,
    modifier: Modifier = Modifier,
) {
    AsyncImage(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .fillMaxSize()
            .clickable { navigateToDetails(movie.id) },
        model = movie.posterPath,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        placeholder = painterResource(
            id = if (isSystemInDarkTheme()) R.drawable.dark_image_place_holder else
                R.drawable.light_image_place_holder
        )

    )
}

@Composable
fun PosterFilmComponentsForCache(
    modifier: Modifier = Modifier,
    navigateToDetails: (Int) -> Unit,
    movie: String,
    movieId: Int
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(30.dp))
//            .border(1.dp, Background, RoundedCornerShape(30.dp))
    ) {
        Column {
            Image(
                modifier = modifier
                    .fillMaxSize()
                    .clickable { navigateToDetails(movieId) },
                painter = rememberAsyncImagePainter(model = movie),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
        }
    }
}