package com.nurmamedova.searchapp.data.models

data class MovieModel (
    val movieId: Int,
    val name: String?,
    val year: String,
    val description: String?,
    val posterUrl: String,
    val posterUrlPreview: String
)