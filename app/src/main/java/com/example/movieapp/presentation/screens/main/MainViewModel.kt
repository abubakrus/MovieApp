package com.example.movieapp.presentation.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.data.cloud.mappers.isUnknown
import com.example.movieapp.data.cloud.mappers.toUi
import com.example.movieapp.domain.usecases.FetchMoviesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetch: FetchMoviesInteractor
) : ViewModel() {
    private val _uiStateFlow = MutableStateFlow<MainScreenUiState>(MainScreenUiState.Loading)
    val uiState: StateFlow<MainScreenUiState> = _uiStateFlow.asStateFlow()

    init {
        fetchAllMovies()
    }

    fun fetchAllMovies() {
        val handler = CoroutineExceptionHandler { _, throwable ->
            _uiStateFlow.tryEmit(MainScreenUiState.Error(throwable.localizedMessage ?: ""))
        }

        viewModelScope.launch(handler + Dispatchers.IO) {
            val popularMovies = fetch.fetchPopularMovies().toUi()
            val topRatedMovies = fetch.fetchTopRatedMovies().toUi()
            val nowPlayingMovies = fetch.fetchNowPlayingMovies().toUi()
            val upComingMovies = fetch.fetchUpComingMovies().toUi()
            if (popularMovies.isUnknown()) {
                _uiStateFlow.tryEmit(MainScreenUiState.Error("Что то пошло не так"))
            } else {
                _uiStateFlow.tryEmit(
                    MainScreenUiState.Loaded(
                        popularMovies = popularMovies,
                        topRatedMovies = topRatedMovies,
                        nowPlayingMovies = nowPlayingMovies,
                        upComingMovies = upComingMovies
                    )
                )
            }
        }
    }
}