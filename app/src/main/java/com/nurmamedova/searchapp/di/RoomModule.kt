package com.nurmamedova.searchapp.di

import android.app.Application
import androidx.room.Room
import com.nurmamedova.searchapp.data.database.MovieDao
import com.nurmamedova.searchapp.data.database.MovieDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideMovieDatabase(application: Application): MovieDatabase {
        return Room.databaseBuilder(application, MovieDatabase::class.java, "movies.db")
            .build()
    }

    @Singleton
    @Provides
    fun provideMovieDao(database: MovieDatabase): MovieDao {
        return database.movieDao()
    }
}