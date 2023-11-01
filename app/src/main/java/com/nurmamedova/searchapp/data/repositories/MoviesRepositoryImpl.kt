package com.nurmamedova.searchapp.data.repositories

import com.nurmamedova.searchapp.data.ApiService
import com.nurmamedova.searchapp.data.database.MovieDao
import com.nurmamedova.searchapp.data.models.MovieModel
import com.nurmamedova.searchapp.domain.repositories.MoviesRepository
import com.nurmamedova.searchapp.data.utils.StatusResponse
import com.nurmamedova.searchapp.data.utils.toMovieEntity
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val apiService: ApiService,
                                               private val movieDao: MovieDao
) : MoviesRepository {

    override suspend fun getMovies() = flow {
        emit(StatusResponse.Loading(true))

        val allMovies = mutableListOf<MovieModel>()

        val maxPage = 10

        for (i in 1..maxPage) {
            val response = apiService.getMovies(page = i)
            allMovies.addAll(response.movies.onEach {

            })
        }
        emit(StatusResponse.Success(allMovies.toList()))

    }.catch { e ->
        emit(StatusResponse.Failure(e.message ?: "Unknown error has occurred"))
    }

    override suspend fun insertMovie(movie: MovieModel) {
        movieDao.insertMovie(movie.toMovieEntity())
    }

    override suspend fun removeMovie(movie: MovieModel) {
        movieDao.deleteMovie(movie.movieId)
    }

}