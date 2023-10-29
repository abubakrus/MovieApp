package com.example.movieapp.presentation.screens.main

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.movieapp.R
import com.example.movieapp.presentation.components.PosterFilmComponent
import com.example.movieapp.presentation.models.FetchTypeUi
import com.example.movieapp.presentation.models.MovieUi
import com.example.movieapp.presentation.models.MovieCategoriesModels
import com.example.movieapp.presentation.theme.Background
import com.example.movieapp.presentation.theme.LedgerFont
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavHostController,
    uiStateFlow: StateFlow<MainScreenUiState>,
    navigateToDetails: (Int) -> Unit,
) {
    val uiState = uiStateFlow.collectAsStateWithLifecycle().value
    val fullScreenModifier = Modifier.fillMaxSize()
    when (uiState) {
        is MainScreenUiState.Loading -> LoadingMainScreen(modifier = fullScreenModifier)

        is MainScreenUiState.Loaded -> LoadedMainScreen(
            navController = navController,
            uiState = uiState,
            navigateToDetails = navigateToDetails,
        )

        is MainScreenUiState.Error -> ErrorMainScreen(
            errorMessage = uiState.message
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
private fun LoadedMainScreen(
    navController: NavHostController,
    uiState: MainScreenUiState.Loaded,
    navigateToDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    val (value, onValueChange) = remember { mutableStateOf("") }
    var nowMovies = remember { uiState.popularMovies }
    val movieCategory = MovieCategoriesModels.getAllMovieCategories()
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Background else Color.White)
    ) {
        val screenHeight = maxHeight
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(state = scrollState)
        ) {
            Column {
                Spacer(modifier = modifier.height(20.dp))
                Text(
                    modifier = modifier.padding(horizontal = 24.dp),

                    text = stringResource(id = R.string.greeting),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.Bold, color = if (isSystemInDarkTheme()) Color.White
                        else Color.Black
                    )
                )
                Spacer(modifier = modifier.height(24.dp))
                OutlinedTextField(
                    value = value,
                    onValueChange = onValueChange,
                    textStyle = TextStyle(fontSize = 17.sp),
                    shape = CircleShape,
                    enabled = false,
                    trailingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = null,
                            tint = Color.Gray
                        )
                    },
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .background(Color(0xFFE7F1F1), RoundedCornerShape(30.dp))
                        .clickable { navController.navigate(BottomNavigationBar.Search.route) },
                    placeholder = { Text(text = "Start Search") },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.DarkGray,
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))
                MainMovieContent(
                    topRatedMovies = uiState.topRatedMovies, navigateToDetails = navigateToDetails
                )
                Spacer(modifier = Modifier.height(12.dp))
            }
            Column(
                modifier = Modifier
                    .height(screenHeight)
                    .background(if (isSystemInDarkTheme()) Background else Color.White),
            ) {
                ScrollableTabRow(
                    selectedTabIndex = pagerState.currentPage,
                    containerColor = if (isSystemInDarkTheme()) Background else Color.White,
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
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
                    movieCategory.forEachIndexed { index, title ->
                        Tab(
                            modifier = Modifier.padding(16.dp),
                            selected = pagerState.currentPage == index,
                            onClick = {
                                coroutineScope.launch {
                                    pagerState.animateScrollToPage(index)
                                }
                                nowMovies = when (title.categoryType) {
                                    FetchTypeUi.POPULAR -> uiState.popularMovies
                                    FetchTypeUi.NOW_PLAYING -> uiState.nowPlayingMovies
                                    FetchTypeUi.UP_COMING -> uiState.upComingMovies
                                    FetchTypeUi.TOP_RATED -> uiState.topRatedMovies
                                }
                            },
                            text = {
                                Text(
                                    text = title.titleResId,
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        color = if (isSystemInDarkTheme()) Color.White
                                        else Color.Black
                                    ),
                                    fontFamily = LedgerFont
                                )
                            },
                        )
                    }
                }
                HorizontalPager(
                    pageCount = movieCategory.size,
                    state = pagerState,
                    modifier = Modifier.background(if (isSystemInDarkTheme()) Background else Color.White),
                    userScrollEnabled = false
                ) {
                    LazyVerticalGrid(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        columns = GridCells.Fixed(3),
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        contentPadding = PaddingValues(start = 12.dp, end = 12.dp, bottom = 40.dp),
                        userScrollEnabled = false,
                        modifier = Modifier
                            .padding(all = 16.dp)
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

                        ) {
                        items(items = nowMovies) { movie ->
                            PosterFilmComponent(
                                navigateToDetails = navigateToDetails,
                                movie = movie,
                                modifier = Modifier
                                    .height(157.dp)
                                    .width(112.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ErrorMainScreen(
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
fun LoadingMainScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.background(if (isSystemInDarkTheme()) Background else Color.White),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun MainMovieContent(
    topRatedMovies: List<MovieUi>,
    navigateToDetails: (Int) -> Unit,
) {
    LazyRow(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 12.dp)
    ) {
        items(items = topRatedMovies) { movie ->
            PosterFilmComponent(
                navigateToDetails = navigateToDetails,
                movie = movie,
                modifier = Modifier
                    .height(220.dp)
                    .width(160.dp)
            )
        }
    }
}