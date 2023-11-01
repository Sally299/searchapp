package com.nurmamedova.searchapp.domain.repositories

import com.nurmamedova.searchapp.data.models.MovieModel
import com.nurmamedova.searchapp.data.utils.StatusResponse
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {
    suspend fun getMovies(): Flow<StatusResponse<List<MovieModel>>>
    suspend fun insertMovie(movie: MovieModel)
    suspend fun removeMovie(movie: MovieModel)
}