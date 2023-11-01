package com.nurmamedova.searchapp.domain.interactors

import com.nurmamedova.searchapp.data.models.MovieModel
import com.nurmamedova.searchapp.domain.repositories.MoviesRepository
import javax.inject.Inject

class MoviesInteractor @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    suspend fun getMovies() = moviesRepository.getMovies()
    suspend fun insertMovie(movie: MovieModel) = moviesRepository.insertMovie(movie)
    suspend fun removeMovie(movie: MovieModel) = moviesRepository.removeMovie(movie)
}