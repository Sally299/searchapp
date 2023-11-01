package com.nurmamedova.searchapp.data.utils

import com.nurmamedova.searchapp.data.models.MovieModel

sealed class StatusResponse<T> {
    data class Loading<T>(val isLoading: Boolean) : StatusResponse<T>()
    data class Success<T>(val data: T) : StatusResponse<T>()
    data class Failure<T>(val errorMessage: String) : StatusResponse<T>()
}

data class MovieDescriptionResponse(
    val movieId: Int,
    val name: String? = null,
    val posterUrl: String,
    val webUrl: String,
    val year: String,
    val description: String? = null,
)

data class MovieResponse(
    val pagesCount: Int,
    val movies: List<MovieModel>
)