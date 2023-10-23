package com.example.movieapp.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.movieapp.presentation.components.BottomBar
import com.example.movieapp.presentation.screens.detail.DetailScreen
import com.example.movieapp.presentation.screens.detail.DetailViewModel
import com.example.movieapp.presentation.screens.main.BottomNavigationBar
import com.example.movieapp.presentation.screens.main.MainScreen
import com.example.movieapp.presentation.screens.main.MainViewModel
import com.example.movieapp.presentation.screens.search.SearchScreen
import com.example.movieapp.presentation.screens.search.SearchViewModel
import com.example.movieapp.presentation.screens.splash.AnimatedSplashScreen
import com.example.movieapp.presentation.screens.watch_list.WatchListScreen
import com.example.movieapp.presentation.screens.watch_list.WatchListViewModel
import com.example.movieapp.presentation.theme.Background

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavGraph(
    navController: NavHostController,
) {
    val currentDestination =
        navController.currentBackStackEntryFlow.collectAsStateWithLifecycle(initialValue = null).value?.destination?.route

    Scaffold(
        contentColor = if (isSystemInDarkTheme()) Background else Color.White,
        containerColor = if (isSystemInDarkTheme()) Background else Color.White,
        bottomBar = {
            if (currentDestination != Screen.Splash.route) {
                BottomBar(navController)
            }
        },
    ) { innerPaddings ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPaddings)
        ) {
            composable(route = Screen.Splash.route) {
                AnimatedSplashScreen(navController)
            }
            composable(route = BottomNavigationBar.Main.route) {
                val viewModel: MainViewModel = hiltViewModel()
                MainScreen(navController = navController,
                    uiStateFlow = viewModel.uiState,
                    navigateToDetails = { movieId ->
                        navController.navigate("${DetailDestination.route}/$movieId")
                    })
            }
            composable(route = BottomNavigationBar.Search.route) {
                val viewModel: SearchViewModel = hiltViewModel()

                SearchScreen(navController = navController,
                    onValueChange = viewModel::onValueChange,
                    uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value,
                    navigateToDetails = { movieId ->
                        navController.navigate("${DetailDestination.route}/$movieId")
                    })
            }
            composable(route = BottomNavigationBar.WatchList.route) {
                val viewModel: WatchListViewModel = hiltViewModel()
                viewModel.fetchAllSavedMovies()
                WatchListScreen(
                    navController = navController,
                    uiStateFlow = viewModel.uiStateFlow,
                    navigateToDetails = { movieId ->
                        navController.navigate("${DetailDestination.route}/$movieId")
                    },
                )
            }
            composable(
                route = DetailDestination.routeWithArgs, arguments = DetailDestination.arguments
            ) { navBackStackEntry ->
                val movieId = navBackStackEntry.arguments?.getInt(DetailDestination.movieIdKey) ?: 0
                val viewModel: DetailViewModel = hiltViewModel()

                DetailScreen(
                    uiStateFlow = viewModel.uiStateFlow,
                    fetchMovie = { viewModel.init(movieId) },
                    navController = navController,
                    addOrDeleteMovie = {
                        viewModel.addOrDeleteMovie(movieId)
                    },
                    onFilterClick = viewModel::onFilterClick
                )
            }

        }
    }
}