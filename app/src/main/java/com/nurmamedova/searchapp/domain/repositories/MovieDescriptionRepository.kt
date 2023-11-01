package com.nurmamedova.searchapp.domain.repositories

import com.nurmamedova.searchapp.data.models.MovieDescriptionModel
import com.nurmamedova.searchapp.data.utils.StatusResponse
import kotlinx.coroutines.flow.Flow

interface MovieDescriptionRepository {
    suspend fun getMovieDescription(movieId: Int): Flow<StatusResponse<MovieDescriptionModel>>
}