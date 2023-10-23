package com.example.movieapp.presentation.screens.watch_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.usecases.FetchAllSavedMoviesImpl
import com.example.movieapp.domain.usecases.FetchAllSavedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WatchListViewModel @Inject constructor(
    private val fetchAllSavedMoviesUseCase: FetchAllSavedMoviesUseCase
) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(WatchListScreenUiState())
    val uiStateFlow: StateFlow<WatchListScreenUiState> = _uiStateFlow.asStateFlow()


    fun fetchAllSavedMovies() {
        fetchAllSavedMoviesUseCase().onEach{ movies ->
            _uiStateFlow.tryEmit(WatchListScreenUiState(movies = movies))
        }.launchIn(viewModelScope)
    }

}