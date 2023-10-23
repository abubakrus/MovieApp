package com.example.movieapp.di

import com.example.movieapp.domain.usecases.FetchAllSavedMoviesImpl
import com.example.movieapp.domain.usecases.FetchAllSavedMoviesUseCase
import com.example.movieapp.domain.usecases.FetchMovieByIdUseCase
import com.example.movieapp.domain.usecases.FetchMovieByIdUseCaseImpl
import com.example.movieapp.domain.usecases.FetchMoviePeoplesUseCase
import com.example.movieapp.domain.usecases.FetchMoviePeoplesUseCaseImpl
import com.example.movieapp.domain.usecases.FetchMovieReviewsUseCase
import com.example.movieapp.domain.usecases.FetchMovieReviewsUseCaseImpl
import com.example.movieapp.domain.usecases.FetchMoviesInteractor
import com.example.movieapp.domain.usecases.FetchMoviesInteractorImpl
import com.example.movieapp.domain.usecases.IsMovieSavedUseCase
import com.example.movieapp.domain.usecases.IsMovieSavedUseCaseImpl
import com.example.movieapp.domain.usecases.MovieOperatorUseCase
import com.example.movieapp.domain.usecases.MovieOperatorUseCaseImpl
import com.example.movieapp.domain.usecases.SearchMoviesByQueryUseCase
import com.example.movieapp.domain.usecases.SearchMoviesByQueryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent


@Module
@InstallIn(ViewModelComponent::class)
interface UseCasesModule {

    @Binds
    fun bindFetchMoviesInteractor(
        implement: FetchMoviesInteractorImpl
    ): FetchMoviesInteractor


    @Binds
    fun bindSearchMoviesByQueryUseCase(
        implement: SearchMoviesByQueryUseCaseImpl
    ): SearchMoviesByQueryUseCase

    @Binds
    fun bindFetchMovieByIdUseCase(
        implement: FetchMovieByIdUseCaseImpl
    ): FetchMovieByIdUseCase

    @Binds
    fun bindFetchAllSavedMovies(
        implement: FetchAllSavedMoviesImpl
    ): FetchAllSavedMoviesUseCase


    @Binds
    fun bindIsMovieSavedUseCaseImpl(
        implement: IsMovieSavedUseCaseImpl
    ): IsMovieSavedUseCase

    @Binds
    fun bindMovieOperatorUseCaseImpl(
        implement: MovieOperatorUseCaseImpl
    ): MovieOperatorUseCase

    @Binds
    fun bindFetchMoviePeoplesUseCaseImpl(
        implement: FetchMoviePeoplesUseCaseImpl
    ): FetchMoviePeoplesUseCase

    @Binds
    fun bindFetchMovieReviewsUseCaseImpl(
        implement: FetchMovieReviewsUseCaseImpl
    ): FetchMovieReviewsUseCase

}