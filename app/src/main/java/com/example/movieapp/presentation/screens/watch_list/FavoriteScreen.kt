package com.example.movieapp.presentation.screens.watch_list

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.presentation.theme.Background
import kotlinx.coroutines.flow.StateFlow

@Composable
fun WatchListScreen(
    uiStateFlow: StateFlow<WatchListScreenUiState>,
    navigateToDetails: (Int) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier,

    ) {
    val uiState = uiStateFlow.collectAsStateWithLifecycle().value


    if (uiState.movies.isEmpty()) {
        WatchListScreenHeader(navController = navController)

        NoResults()
    } else LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Background else Color.White)
    ) {
        item {
            WatchListScreenHeader(navController = navController)
        }
        items(items = uiState.movies) { movie ->
            IncludeWatch(
                navigateToDetails = navigateToDetails,
                posterUrl = movie.posterPath,
                movieId = movie.id,
                title = movie.title,
                voteAverage = movie.voteAverage.toString(),
                releaseDate = movie.releaseDate,
                runtime = movie.runtime
            )
        }

    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun IncludeWatch(
    navigateToDetails: (Int) -> Unit,
    posterUrl: String,
    movieId: Int,
    title: String,
    voteAverage: String,
    releaseDate: String,
    runtime: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.padding(20.dp)
    ) {
        AsyncImage(
            modifier = modifier
                .height(160.dp)
                .width(112.dp)
                .clip(RoundedCornerShape(30.dp))
                .clickable { navigateToDetails(movieId) },
            model = posterUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(
                id = if (isSystemInDarkTheme()) R.drawable.dark_image_place_holder
                else R.drawable.light_image_place_holder
            )
        )
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.Normal,
                    color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                ),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row() {
                Icon(
                    modifier = Modifier.size(20.dp),
                    imageVector = Icons.Default.DateRange,
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = releaseDate,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = if (isSystemInDarkTheme()) Color.White
                        else Color.Black
                    ),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.starr),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = voteAverage.toString(),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = if (isSystemInDarkTheme()) Color.White
                        else Color.Black
                    ),
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Row(
            ) {
                Icon(
                    modifier = Modifier.size(20.dp),
                    painter = painterResource(id = R.drawable.clock_icon),
                    contentDescription = null,
                    tint = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = runtime.toString() + "min",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal,
                        color = if (isSystemInDarkTheme()) Color.White
                        else Color.Black
                    ),
                )
            }
        }
    }
}


@Composable
fun WatchListScreenHeader(
    modifier: Modifier = Modifier, navController: NavHostController
) {
    Column(
        modifier = modifier.padding(top = 16.dp)
    ) {
        Row(
            modifier = modifier.padding(horizontal = 12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = modifier
                    .size(35.dp)
                    .clickable { navController.navigateUp() },
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                tint = if (isSystemInDarkTheme()) Color.White
                else Color.Black
            )
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.watch_list),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold, color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                )
            )
            Spacer(modifier = modifier.weight(1f))
        }
    }
}

@Composable
fun ErrorWatchScreen(
    errorMessage: String, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        Text(
            text = errorMessage, style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun LoadingWatchScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun NoResults(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Image(
                painter = painterResource(id = R.drawable.box_watch),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.sorry_room),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Normal, color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                ),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.find_your),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Normal, color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                )
            )
        }
    }
}