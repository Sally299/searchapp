package com.nurmamedova.searchapp.data.utils

import com.nurmamedova.searchapp.data.database.MovieEntity
import com.nurmamedova.searchapp.data.models.MovieDescriptionModel
import com.nurmamedova.searchapp.data.models.MovieModel

fun MovieModel.toMovieEntity(): MovieEntity {
    return MovieEntity(
        movieId = this.movieId,
        name = this.name,
        year = this.year,
        posterUrl = this.posterUrl,
        posterUrlPreview = this.posterUrlPreview,
        description = this.description
    )
}

fun MovieDescriptionResponse.toMovieDescriptionModel(): MovieDescriptionModel {
    return MovieDescriptionModel(
        this.movieId,
        this.name,
        this.posterUrl,
        this.webUrl,
        this.year,
        this.description
    )
}