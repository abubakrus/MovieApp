package com.example.movieapp.presentation.screens.search

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.movieapp.R
import com.example.movieapp.data.cloud.remote.POSTER_PATH_URL
import com.example.movieapp.presentation.screens.watch_list.IncludeWatch
import com.example.movieapp.presentation.theme.Background

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    uiState: SearchUiState,
    onValueChange: (String) -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    navigateToDetails: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .padding(top = 16.dp)
            .background(if (isSystemInDarkTheme()) Background else Color.White)
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
            )
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = stringResource(id = R.string.search),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold, color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                )
            )
            Spacer(modifier = modifier.weight(1f))
        }
        Spacer(modifier = modifier.height(16.dp))
        OutlinedTextField(
            value = uiState.query,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 17.sp),
            shape = CircleShape,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search, contentDescription = null, tint = Color.Gray
                )
            },
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 26.dp)
                .background(Color(0xFFE7F1F1), RoundedCornerShape(30.dp)),
            placeholder = { Text(text = "Start Search") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                cursorColor = Color.DarkGray,
            )
        )
        when {
            uiState.movies.isEmpty() -> NoResultsStub()
            uiState.isLoading -> LoadingScreen()
            else -> {
                LazyColumn {
                    items(
                        items = uiState.movies
                    ) { movie ->
                        IncludeWatch(
                            navigateToDetails = navigateToDetails,
                            posterUrl = POSTER_PATH_URL + movie.posterPath,
                            movieId = movie.id,
                            title = movie.title,
                            voteAverage = movie.voteAverage.toString(),
                            releaseDate = movie.releaseDate,
                            runtime = movie.runtime
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Background else Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun NoResultsStub(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.no_results),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = stringResource(id = R.string.sorry_movie),
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