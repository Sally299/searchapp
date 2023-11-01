package com.nurmamedova.searchapp.data.models

data class MovieDescriptionModel(
    val movieId: Int,
    val name: String? = null,
    val posterUrl: String,
    val webUrl: String,
    val year: String,
    val description: String? = null
)