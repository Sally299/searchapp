package com.nurmamedova.searchapp.domain.interactors

import com.nurmamedova.searchapp.domain.repositories.MovieDescriptionRepository
import javax.inject.Inject

class MovieDescriptionInteractor @Inject constructor(
    private val movieDescriptionRepository: MovieDescriptionRepository
) {

    suspend fun getMovieDescription(movieId: Int) =
        movieDescriptionRepository.getMovieDescription(movieId)
}