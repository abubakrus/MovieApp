package com.example.movieapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.movieapp.presentation.models.MovieUi
import com.example.movieapp.presentation.navigation.SetupNavGraph
import com.example.movieapp.presentation.screens.main.MainViewModel
import com.example.movieapp.presentation.theme.Background
import com.example.movieapp.presentation.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel
        setContent {
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = if (isSystemInDarkTheme()) Background else Color.White,
                    contentColor = if (isSystemInDarkTheme()) Background else Color.White,
                ) {
                }
                val navController = rememberNavController()
                SetupNavGraph(navController = navController)
            }
        }
    }
}
