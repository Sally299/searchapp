package com.nurmamedova.searchapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Flow<List<MovieEntity>>

    @Query("DELETE FROM movies WHERE movieId = :movieId")
    suspend fun deleteMovie(movieId: Int)

    @Insert
    suspend fun insertMovie(movie: MovieEntity)

}