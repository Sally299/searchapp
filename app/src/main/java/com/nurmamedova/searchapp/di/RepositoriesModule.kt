package com.nurmamedova.searchapp.di

import com.nurmamedova.searchapp.data.repositories.MovieDescriptionRepositoryImpl
import com.nurmamedova.searchapp.data.repositories.MoviesRepositoryImpl
import com.nurmamedova.searchapp.domain.repositories.MovieDescriptionRepository
import com.nurmamedova.searchapp.domain.repositories.MoviesRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoriesModule {

    @Provides
    fun provideMoviesRepository(repositoryImpl: MoviesRepositoryImpl):
            MoviesRepository = repositoryImpl

    @Provides
    fun provideMovieDescriptionRepository(repositoryImpl: MovieDescriptionRepositoryImpl):
            MovieDescriptionRepository = repositoryImpl

}