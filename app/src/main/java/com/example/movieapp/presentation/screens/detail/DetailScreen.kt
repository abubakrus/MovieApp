package com.example.movieapp.presentation.screens.detail

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.movieapp.R
import com.example.movieapp.domain.models.MovieDetailsDomain
import com.example.movieapp.domain.models.PeopleDomain
import com.example.movieapp.presentation.components.ReviewersFilterPopup
import com.example.movieapp.presentation.components.ReviewsItemList
import com.example.movieapp.presentation.theme.Background
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@Composable
fun DetailScreen(
    uiStateFlow: StateFlow<DetailsScreenUiState>,
    navController: NavHostController,
    fetchMovie: () -> Unit,
    addOrDeleteMovie: () -> Unit,
    onFilterClick: (SortByItems) -> Unit,
    modifier: Modifier = Modifier,

    ) {
    val uiState = uiStateFlow.collectAsStateWithLifecycle().value
    val fullScreenModifier = Modifier.fillMaxSize()
    val i = Log.i("AbuAcademy", "uiState = ${uiState}")

    LaunchedEffect(key1 = Unit) { fetchMovie() }
    when (uiState) {
        is DetailsScreenUiState.Loading -> LoadingDetailScreen(
            modifier = fullScreenModifier,
        )

        is DetailsScreenUiState.Loaded -> LoadedDetailScreen(
            navController = navController,
            uiState = uiState,
            addOrDeleteMovie = addOrDeleteMovie,
            isSaved = uiState.isSaved,
            onFilterClick = onFilterClick
        )

        is DetailsScreenUiState.Error -> ErrorDetailScreen(
            errorMessage = uiState.message
        )


    }

}

@OptIn(ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
private fun LoadedDetailScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    uiState: DetailsScreenUiState.Loaded,
    addOrDeleteMovie: () -> Unit,
    onFilterClick: (SortByItems) -> Unit,
    isSaved: Boolean
) {
    val scrollState = rememberScrollState()
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    var screenHeight: Dp

    BoxWithConstraints(
        modifier = modifier.fillMaxSize()
    ) {
        screenHeight = maxHeight
        Column(
            modifier = modifier
                .fillMaxWidth()
                .verticalScroll(state = scrollState)
        ) {

            Column {
                DetailsScreenHeader(
                    addOrDeleteMovie = addOrDeleteMovie,
                    isSaved = isSaved,
                    navController = navController
                )
                Spacer(modifier = modifier.height(30.dp))

                DetailBackgroundContent(movie = uiState.movie)
                Spacer(modifier = modifier.height(12.dp))

                FilmTime(
                    modifier = Modifier.padding(start = 20.dp), movie = uiState.movie
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            Column(
                modifier = modifier.height(screenHeight)
            ) {
                ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = if (isSystemInDarkTheme()) Background else Color.White,
                    modifier = modifier
                        .padding(horizontal = 16.dp)
                        .fillMaxWidth(),
                    indicator = { tabPositions ->
                        Box(
                            modifier = Modifier
                                .tabIndicatorOffset(tabPositions[pagerState.currentPage])
                                .height(4.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.onBackground,
                                    shape = RoundedCornerShape(8.dp)
                                )
                        )

                    },
                    divider = {
                        Spacer(modifier = Modifier.height(4.dp))
                    },
                    edgePadding = 0.dp
                ) {
                    uiState.tabs.forEachIndexed { index, detailTab ->
                        Tab(modifier = Modifier.padding(16.dp),
                            selected = index == pagerState.currentPage,
                            onClick = {
                                scope.launch { pagerState.animateScrollToPage(index) }
                            }) {
                            Text(
                                text = stringResource(id = detailTab.titleResId),
                                style = MaterialTheme.typography.titleLarge,
                                color = if (isSystemInDarkTheme()) Color.White else Color.Black
                            )
                        }

                    }
                }
                HorizontalPager(
                    modifier = Modifier
                        .fillMaxWidth()
                        .nestedScroll(remember {
                            object : NestedScrollConnection {
                                override fun onPreScroll(
                                    available: Offset, source: NestedScrollSource
                                ): Offset {
                                    return if (available.y > 0) Offset.Zero else Offset(
                                        x = 0f, y = -scrollState.dispatchRawDelta(-available.y)
                                    )
                                }
                            }
                        }),
                    pageCount = uiState.tabs.size,
                    state = pagerState,
                ) { index ->
                    when (val tab = uiState.tabs[index]) {
                        is DetailTab.AboutMovie -> {
                            AboutFilm(overview = tab.about)
                        }

                        is DetailTab.Reviewers -> {
                            var isFilterClick by remember {
                                mutableStateOf(false)
                            }
                            Column {
                                Row(
                                    modifier = Modifier.padding(horizontal = 24.dp)
                                ) {
                                    Text(
                                        text = stringResource(id = R.string.filter),
                                        style = MaterialTheme.typography.titleLarge,
                                        color = MaterialTheme.colorScheme.onBackground
                                    )
                                    Spacer(modifier = Modifier.weight(1f))
                                    Column {
                                        if (isFilterClick) ReviewersFilterPopup(onClick = { sort ->
                                            isFilterClick = false
                                            onFilterClick(sort)
                                        })
                                        Icon(
                                            modifier = modifier.clickable {
                                                isFilterClick = !isFilterClick
                                            },
                                            painter = painterResource(id = R.drawable.filter_icon),
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.onBackground
                                        )

                                    }
                                }
                                ReviewsItemList(reviewersFlow = tab.reviews)
                            }
                        }

                        is DetailTab.Crews -> {
                            PeopleList(people = tab.crews)
                        }

                        is DetailTab.Casts -> {
                            PeopleList(people = tab.casts)

                        }
                    }

                }
            }

        }
    }
}


@Composable
fun ErrorDetailScreen(
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
fun LoadingDetailScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun DetailBackgroundContent(
    movie: MovieDetailsDomain
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 25.dp, bottomEnd = 25.dp)),
            model = movie.backdropPath,
            contentDescription = null
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 25.dp)
                .align(Alignment.BottomStart)
        ) {
            AsyncImage(
                modifier = Modifier.clip(RoundedCornerShape(25.dp)),
                model = movie.posterPath,
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                modifier = Modifier.align(alignment = Alignment.CenterVertically),
                text = movie.title,
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold, color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                ),
            )
        }
    }
}

@Composable
fun DetailsScreenHeader(
    isSaved: Boolean,
    addOrDeleteMovie: () -> Unit,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(top = 16.dp)
    ) {
        Row(
            modifier = modifier.padding(horizontal = 12.dp),
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
                text = stringResource(id = R.string.detail),
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold, color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black
                )
            )
            Spacer(modifier = modifier.weight(1f))
            Icon(
                modifier = modifier
                    .size(35.dp)
                    .clickable { addOrDeleteMovie() },
                painter = painterResource(
                    id = if (isSaved) R.drawable.save_icon else R.drawable.un_saved_icon
                ),
                contentDescription = null,
                tint = if (isSystemInDarkTheme()) Color.White
                else Color.Black
            )
        }
    }
}

@Composable
fun FilmTime(
    modifier: Modifier = Modifier, movie: MovieDetailsDomain
) {
    Row(
        modifier = modifier.padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = modifier.size(25.dp),
            painter = painterResource(id = R.drawable.calendar_blank),
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) Color.White
            else Color.Black
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = movie.releaseDate,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Normal, color = if (isSystemInDarkTheme()) Color.White
                else Color.Black
            ),
        )
        Icon(
            modifier = modifier.size(25.dp),
            painter = painterResource(id = R.drawable.clock_icon),
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) Color.White
            else Color.Black
        )
        Spacer(modifier = Modifier.width(6.dp))
        Text(
            text = "${movie.runtime.toString()} min ",
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Normal, color = if (isSystemInDarkTheme()) Color.White
                else Color.Black
            ),
        )
    }
}

@Composable
fun AboutFilm(
    overview: String, modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .padding(30.dp)
            .fillMaxWidth(),
        text = overview,
        style = MaterialTheme.typography.titleSmall.copy(

            fontWeight = FontWeight.Normal, color = if (isSystemInDarkTheme()) Color.White
            else Color.Black
        ),
        textAlign = TextAlign.Center
    )

}


@Composable
fun PeopleList(
    people: List<PeopleDomain>, modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(65.dp)
    ) {
        items(
            items = people
        ) { people ->
            PeopleItem(people)
        }
    }
}

@Composable
fun PeopleItem(
    people: PeopleDomain, modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        AsyncImage(
            model = people.profilePath,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            placeholder = painterResource(
                id = if (isSystemInDarkTheme()) R.drawable.dark_image_place_holder
                else R.drawable.light_image_place_holder
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = people.name,
            style = MaterialTheme.typography.bodyMedium.copy(
                color = if (isSystemInDarkTheme()) Color.White else Color.Black
            ),
            textAlign = TextAlign.Center
        )

    }

}
