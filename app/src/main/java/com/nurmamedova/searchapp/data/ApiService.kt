package com.nurmamedova.searchapp.data

import com.nurmamedova.searchapp.data.utils.MovieDescriptionResponse
import com.nurmamedova.searchapp.data.utils.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("v2.2/films/top")
    suspend fun getMovies(
        @Query("type") type: String = "TOP_100_POPULAR_FILMS",
        @Query("page") page: Int = 1
    ): MovieResponse

    @GET("v2.2/films/{id}")
    suspend fun getMovieDescription(@Path("id") id: Int): MovieDescriptionResponse
}