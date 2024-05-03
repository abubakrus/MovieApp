package com.example.movieapp.presentation.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapp.domain.models.ActorsDomain
import com.example.movieapp.domain.models.ReviewsDomain
import com.example.movieapp.domain.usecases.FetchMovieByIdUseCase
import com.example.movieapp.domain.usecases.FetchMoviePeoplesUseCase
import com.example.movieapp.domain.usecases.FetchMovieReviewsUseCase
import com.example.movieapp.domain.usecases.IsMovieSavedUseCase
import com.example.movieapp.domain.usecases.MovieOperatorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DetailViewModel @Inject constructor(
    private val fetchMovieByIdUseCase: FetchMovieByIdUseCase,
    private val isMovieSavedUseCase: IsMovieSavedUseCase,
    private val movieOperatorUseCase: MovieOperatorUseCase,
    private val fetchMoviePeoplesUseCase: FetchMoviePeoplesUseCase,
    private val fetchMovieReviewsUseCase: FetchMovieReviewsUseCase,
) : ViewModel() {
    private val _uiStateFlow = MutableStateFlow<DetailsScreenUiState>(DetailsScreenUiState.Loading)
    val uiStateFlow: StateFlow<DetailsScreenUiState> = _uiStateFlow.asStateFlow()
    private val handler = CoroutineExceptionHandler { _, throwable ->
        _uiStateFlow.tryEmit(DetailsScreenUiState.Error(throwable.localizedMessage ?: ""))
    }

    private val sortByFlow = MutableStateFlow(SortByItems.BY_RATING)
    private val reviewersFlow = MutableStateFlow<List<ReviewsDomain>>(emptyList())

    init {
        sortByFlow.combine(reviewersFlow) { sortBy, reviewrs ->
            when (sortBy) {
                SortByItems.BY_RATING -> reviewrs.sortedByDescending { it.reviewsDetails.rating }
                SortByItems.BY_RATING_DOWN -> reviewrs.sortedBy { it.reviewsDetails.rating }
                SortByItems.BY_ADDED -> reviewrs.sortedByDescending { it.createdAt }
                SortByItems.BY_ADDED_DOWN -> reviewrs.sortedBy { it.createdAt }
            }
        }.onEach { reviewers ->
            reviewersFlow.tryEmit(reviewers)
        }.launchIn(viewModelScope)
    }

    fun init(movieId: Int) {
        fetchMovieById(movieId)
    }

    fun onFilterClick(sortBy: SortByItems) {
        sortByFlow.tryEmit(sortBy)
    }

    private fun fetchMovieById(id: Int) {
        viewModelScope.launch(handler + Dispatchers.IO) {
            _uiStateFlow.tryEmit(DetailsScreenUiState.Loading)
            val movieDetail = fetchMovieByIdUseCase.fetchMovieById(id)
            val actorsDomain = fetchMoviePeoplesUseCase(id)
            val reviews = fetchMovieReviewsUseCase(id)
            reviewersFlow.tryEmit(reviews)
            if (movieDetail == null) {
                _uiStateFlow.tryEmit(DetailsScreenUiState.Error("Sorry error"))
            } else {
                _uiStateFlow.tryEmit(
                    DetailsScreenUiState.Loaded(
                        movie = movieDetail,
                        tabs = createTabsByParams(
                            aboutMovie = movieDetail.overview,
                            actorsDomain = actorsDomain,
                        ),
                    )
                )
                checkIsMovieSaved(id)
            }
        }
    }

    private fun createTabsByParams(
        aboutMovie: String, actorsDomain: ActorsDomain,
    ): List<DetailTab> = listOf(
        DetailTab.AboutMovie(aboutMovie),
        DetailTab.Reviewers(reviewersFlow),
        DetailTab.Casts(actorsDomain.peoples),
        DetailTab.Crews(actorsDomain.crews)
    )

    fun addOrDeleteMovie(movieId: Int) {
        val uiState = _uiStateFlow.value as? DetailsScreenUiState.Loaded ?: return
        viewModelScope.launch(Dispatchers.IO) {
            if (uiState.isSaved) {
                movieOperatorUseCase.removeMovie(movieId)
                checkIsMovieSaved(movieId)
            } else {
                movieOperatorUseCase.saveMovie(uiState.movie)
                checkIsMovieSaved(movieId)
            }
        }
    }

    private fun checkIsMovieSaved(movieId: Int) {
        isMovieSavedUseCase.invoke(movieId).onEach { isSaved: Boolean ->
            val uiState = _uiStateFlow.value as? DetailsScreenUiState.Loaded ?: return@onEach
            _uiStateFlow.tryEmit(uiState.copy(isSaved = isSaved))
        }.launchIn(viewModelScope)
    }
}