package com.nurmamedova.searchapp.data.repositories

import com.nurmamedova.searchapp.data.ApiService
import com.nurmamedova.searchapp.domain.repositories.MovieDescriptionRepository
import com.nurmamedova.searchapp.data.utils.StatusResponse
import com.nurmamedova.searchapp.data.utils.toMovieDescriptionModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDescriptionRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MovieDescriptionRepository {

    override suspend fun getMovieDescription(movieId: Int) =
        flow {
            emit(StatusResponse.Loading(true))
            val response = apiService.getMovieDescription(movieId)
            emit(StatusResponse.Success(response.toMovieDescriptionModel()))
        }.catch { e ->
            emit(StatusResponse.Failure(e.message ?: "Unknown error has occurred"))
        }
}